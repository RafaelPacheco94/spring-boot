package org.example.mapper;

import org.example.dto.AdminDTO;
import org.example.entity.tertiary.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(target = "name", source = "name")
    Admin AdminDtoToAdmin(AdminDTO adminDTO);

    @Mapping(target = "name", source = "name")
    AdminDTO AdminToAdminDTO(Admin admin);

}
