# Presentium - Backend API

![GitHub Release](https://img.shields.io/github/v/release/presentium/api)
![GitHub License](https://img.shields.io/github/license/presentium/api)

[![Continuous Integration > Unit Testing + Linting + Formatting](https://github.com/presentium/api/actions/workflows/check.yml/badge.svg)](https://github.com/presentium/api/actions/workflows/check.yml)
[![Continuous Delivery > Docker build and deployment triggers](https://github.com/presentium/api/actions/workflows/docker-images.yml/badge.svg)](https://github.com/presentium/api/actions/workflows/docker-images.yml)

[![App Status](https://cd.presentium.ch/api/badge?name=presentium-api-staging&revision=true&showAppName=true&namespace=argocd)](https://cd.presentium.ch/applications/presentium-api-staging)  [![App Status](https://cd.presentium.ch/api/badge?name=presentium-api-prod&revision=true&showAppName=true&namespace=argocd)](https://cd.presentium.ch/applications/presentium-api-prod)

This is the backend API for Presentium, handling connections with the databse, the reader devices and frontend
instances. It is built with [Spring Boot](https://spring.io/projects/spring-boot) in Java 21.

## Stack

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Hibernate](https://hibernate.org/)
- [Liquibase Migrations](https://www.liquibase.com/)
- [Mapstruct](https://mapstruct.org/)

The project is built with [Gradle](https://gradle.org/).

## API Documentation

The API documentation is available at [api.presentium.ch/swagger-ui.html](https://api.presentium.ch/swagger-ui.html).
It is built automatically using [Springdoc](https://springdoc.org/) and available on the local instance as well.

## Development

Start the development server on `http://localhost:13000`:

```bash
./gradlew bootRun
```

Or by using the `dev profile [start]` profile in IntelliJ IDEA, stored in the `.run` folder.

### Development Tips

#### Local environment

We leverage
[Spring Boot's Docker Compose module](https://docs.spring.io/spring-boot/reference/features/dev-services.html#features.dev-services.docker-compose)
to provision the local development environment. This includes a PostgreSQL database, a `dex` server acting as a OIDC
provider, and a Redis instance for session data and caching.

Running the API server in a development environment will automatically call `docker compose up` and add the necessary
connection details to the application context.

> [!TIP]
> When testing, either manually or in integration / end-to-end tests, you can use the `TestcontainersConfiguration` which
> provides the same environment as the local development setup, but it is volatile and is reset between each runs.
> 
> A Spring runner is also configured to use the Testcontainers configuration in `TestPresentiumApiApplication`. Which
> can be started using the `./gradlew bootTestRun` command.

#### Database migrations

When changing the database model, one should ensure that the changes are correctly defined in the Liquibase migrations.

Liquibase migrations shall always be placed under the app version they are being introduced. They can be found in the
`src/main/resources/db/changelog` directory.

Migration files are named with a prefix that corresponds to the GitHub Issue number that the task is related to,
followed by a short description of the task. For example, `GH-1_add-users-table.xml`.

Each version directory contains a local `_changelog.yaml` file that should be referenced in the main `changelog.yaml`
file.

> [!NOTE]
> We use `YAML` files for indexes as it is easier to follow when there is only simple references.
> For actual changelog files, please use the `XML` format, as it makes sure the changes can be correctly validated.

To help with creating changelog files, a unit test `DDLGeneratorTest` should be run once. It will generate the DDL in
`sql/ddl/create.sql`, which can be used as a reference for the new changelog file (for example by using diffs).

#### API / Business separation

This application is split into standard layers. The business layer shall be responsible of handling
the business model and its logic (using entities, repositories, services, etc.), while the API layer
shall be responsible for handling API calls and responses. 

Therefore, the application should mainly work using business entities at all times, and the API layer
will use mappers to convert said business entities to view models that represent exposed information.
The API also defines request bodies for request requiring it, using mappers to convert them to business
entities straight away.

Controllers are responsible for opening transactions, calling services, and returning the response. 
They are responsible for ensuring that the user making the call is authorized to do so, validating
that the sent data is correct, then calling the business service to perform the operation. If the
business service sends a response, the controller should convert it to a view model and return it,
handling any exceptions should one arise.

##### File structure

- `api` package contains the API layer, with controllers, mappers, and view models.
- `business` package contains the business layer, with entities, repositories, services, and mappers.

Inside packages, we try to separate concerns by their business domain. For example, the `user` domain
is held in `api.user` for the API layer, and `business.model.user` for the data domain.

#### Testing secured endpoints

In theory, all endpoints should require authentication, but the access level can be changed depending
on if it is reserved for teachers or teacher and students. To test these endpoints, you can use the
`@WithMockAuthenticatedUser` annotation and sub-annotations, like `@WithMockTeacherUser`, 
`@WithMockStudentUser`, or `@WithMockAdminUser`.

#### Trying out the API in development

Using the local development environment provisioned with docker compose, you can generate an access
token by using the mock oidc debugger at http://localhost:17580/presentium/debugger. You will simply
need to configure the scope to `openid profile email` and get the token.

You can then use the Swagger UI to try out the API at http://localhost:13000/swagger-ui/index.html.
You will need to provide the token in the `Authorize` button at the top right of the page, the token
to use is the `access_token` field from the OIDC debugger.

Testing gRPC calls is trickier due to the mTLS (mutual TLS) requirement. If you have
[grpcui installed](https://github.com/fullstorydev/grpcui), you can use the following command to
attach to the api

```bash
grpcui \
  -bind=0.0.0.0 -port=8080 \
  -cacert=src/test/resources/docker/vault/presentium_servers_inter_ca.pem \
  -cert=src/test/resources/docker/vault/device.crt \
  -key=src/test/resources/docker/vault/device.key \
  localhost:13050
```

## Continuous Delivery

Each commit on the `main` branch is deployed to [staging-api.presentium.ch](https://staging-api.presentium.ch)
using GitHub Actions, to a dedicated namespace on the Cloud infrastructure.

The default branch therefore is `main` and it is protected. When a change is ready to be deployed,
the deployment workflow should be triggered manually providing the desired version number.

## Contributing

Please refer to the [Contributing Guide][contributing] before making a pull request.

[contributing]: https://github.com/presentium/meta/blob/main/CONTRIBUTING.md
