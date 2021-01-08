package org.example.mapper;

import org.example.dto.EmployeeDTO;
import org.example.entity.secondary.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "name", source = "name")
    Employee EmployeeDtoToEmployee(EmployeeDTO employeeDTO);

    @Mapping(target = "name", source = "name")
    EmployeeDTO EmployeeToEmployeeDto(Employee employee);

}