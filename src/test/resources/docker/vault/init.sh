#!/bin/bash

# Create admin policy
vault policy write admin admin-policy.hcl > /dev/null

# Root CA
export ISSUER_NAME="Presentium Root"
export ISSUER_REF="presentium_root_ca"
vault secrets enable -path=pki_ca pki > /dev/null
vault secrets tune -max-lease-ttl=87600h pki_ca > /dev/null
vault write -field=certificate pki_ca/root/generate/internal \
    common_name="$ISSUER_NAME" \
    issuer_name="$ISSUER_REF" \
    key_type="ec" \
    key_bits=384 \
    organization="Presentium" \
    ttl=87600h > $ISSUER_REF.crt

# Set the pki's urls
vault write pki_ca/config/urls \
    issuing_certificates="$VAULT_ADDR/v1/pki_ca/ca" \
    crl_distribution_points="$VAULT_ADDR/v1/pki_ca/crl" > /dev/null

# Servers Intermediate CA
export INTER_NAME="Presentium Servers Intermediate"
export INTER_REF="presentium_servers_inter_ca"
vault secrets enable -path=pki_int pki > /dev/null
vault secrets tune -max-lease-ttl=43800h pki_int > /dev/null
vault write -field=csr pki_int/intermediate/generate/internal \
    common_name="$INTER_NAME" \
    issuer_name="$INTER_REF" \
    key_type="ec" \
    key_bits=384 \
    organization="Presentium" \
    > $INTER_REF.csr
vault write -field=certificate pki_ca/root/sign-intermediate \
    issuer_ref="$ISSUER_REF" \
    csr=@$INTER_REF.csr \
    format=pem_bundle ttl="43800h" \
    > $INTER_REF.pem
export ISSUERS="$(vault write -field=imported_issuers pki_int/intermediate/set-signed certificate=@$INTER_REF.pem)"

# split [uuid1 uuid2] remove brackets
vault write pki_int/issuer/$(echo $ISSUERS | cut -d' ' -f1 | sed 's/^.//') \
    issuer_name=$INTER_REF > /dev/null
vault write pki_int/issuer/$(echo $ISSUERS | cut -d' ' -f2 | sed 's/.$//') \
    issuer_name=$ISSUER_REF > /dev/null

# Devices Intermediate CA
export INTER_NAME="Presentium Devices Intermediate"
export INTER_REF="presentium_devices_inter_ca"
vault write -field=csr pki_int/intermediate/generate/internal \
    common_name="$INTER_NAME" \
    issuer_name="$INTER_REF" \
    key_type="ec" \
    key_bits=384 \
    organization="Presentium" \
    > $INTER_REF.csr
vault write -field=certificate pki_ca/root/sign-intermediate \
    issuer_ref="$ISSUER_REF" \
    csr=@$INTER_REF.csr \
    format=pem_bundle ttl="43800h" \
    > $INTER_REF.pem
export ISSUERS="$(vault write -field=imported_issuers pki_int/intermediate/set-signed certificate=@$INTER_REF.pem)"

vault write pki_int/issuer/$(echo $ISSUERS | sed 's/^.//;s/.$//') \
    issuer_name=$INTER_REF > /dev/null

# Set the pki_int's urls
vault write pki_int/config/urls \
    issuing_certificates="$VAULT_ADDR/v1/pki_int/ca" \
    crl_distribution_points="$VAULT_ADDR/v1/pki_int/crl" > /dev/null

# Create servers role
export BASE_ROLE_NAME="presentium-dot-ch"
export INTRA_ROLE_NAME="servers-$BASE_ROLE_NAME"
export ROOT_DOMAIN="presentium.ch"
export DOMAIN="api.$ROOT_DOMAIN"
vault write pki_int/roles/$INTRA_ROLE_NAME \
    issuer_ref="presentium_servers_inter_ca" \
    allowed_domains="$DOMAIN" \
    allow_bare_domains=true \
    allow_subdomains=false \
    allow_wildcard_certificates=false \
    allow_localhost=true \
    key_type="ec" \
    key_bits=384 \
    server_flag=true \
    client_flag=false \
    ou="Servers" \
    organization="Presentium" \
    max_ttl="720h" > /dev/null

# Create the servers policy
vault policy write servers servers-policy.hcl > /dev/null

# Create the devices policy
export BASE_ROLE_NAME="presentium-dot-ch"
export INTRA_ROLE_NAME="devices-$BASE_ROLE_NAME"
export ROOT_DOMAIN="presentium.ch"
export DOMAIN="device.$ROOT_DOMAIN"
vault write pki_int/roles/$INTRA_ROLE_NAME \
    issuer_ref="presentium_devices_inter_ca" \
    allowed_domains="$DOMAIN" \
    allow_bare_domains=false \
    allow_subdomains=true \
    allow_wildcard_certificates=false \
    allow_localhost=false \
    allow_ip_sans=false \
    key_type="ec" \
    key_bits=384 \
    server_flag=false \
    client_flag=true \
    ou="Devices" \
    organization="Presentium" \
    max_ttl="720h" > /dev/null

# Create the devices policy
vault policy write devices devices-policy.hcl > /dev/null

export CLIENT_CERT=$(vault write -format=json pki_int/issue/devices-presentium-dot-ch common_name="super-cattle.device.presentium.ch" ttl="720h")
echo $CLIENT_CERT | jq -r '.data.certificate' > device.crt
echo $CLIENT_CERT | jq -r '.data.private_key' > device.key

echo "Vault initialized"
