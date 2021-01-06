package org.example.mapper;

import org.example.dto.UserDTO;
import org.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "age", source = "age"),
            @Mapping(target = "id", ignore = true)
    })
    User userDtoToUser(UserDTO userDTO);


    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "age", source = "age"),
    })
    UserDTO userToUserDto(User user);

}
