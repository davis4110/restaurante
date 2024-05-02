package com.restaurante.infrastructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.restaurante.domain.Employee;
import com.restaurante.infrastructure.entity.EmployeeEntity;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "identification", target = "identification"),
                    @Mapping(source = "firstName", target = "firstName"),
                    @Mapping(source = "lastName", target = "lastName"),
                    @Mapping(source = "email", target = "email"),
                    @Mapping(source = "address", target = "address"),
                    @Mapping(source = "cellphone", target = "cellphone"),
                    @Mapping(source = "dateCreated", target = "dateCreated")
            }
    )

    Employee toEmployee(EmployeeEntity employeeEntity);

    Iterable<Employee> toEmployees(Iterable<EmployeeEntity> employeeEntities);

    @InheritInverseConfiguration
    EmployeeEntity toEmployeeEntity(Employee employee);
}
