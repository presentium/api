package ch.presentium.backend.api.security.mapper;

import ch.presentium.backend.api.security.model.UserViewModel;
import ch.presentium.backend.business.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.oauth2.jwt.Jwt;

@Mapper
public interface UserMapper {
    @Mapping(target = "username", source = "subject")
    UserViewModel toViewModel(User user);

    @Mapping(target = "id", source = "subject")
    @Mapping(target = "subject", expression = "java(jwt.getClaimAsString(\"username\"))")
    @Mapping(target = "email", expression = "java(jwt.getClaimAsString(\"email\"))")
    @Mapping(target = "firstName", expression = "java(jwt.getClaimAsString(\"given_name\"))")
    @Mapping(target = "lastName", expression = "java(jwt.getClaimAsString(\"family_name\"))")
    void updateUser(Jwt jwt, @MappingTarget User user);
}
