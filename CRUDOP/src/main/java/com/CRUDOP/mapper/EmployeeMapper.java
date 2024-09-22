package com.CRUDOP.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.CRUDOP.entities.Employee;
import com.CRUDOP.payloads.EmployeeDto;

public interface EmployeeMapper {

    public Employee mapToEntity(EmployeeDto employeeDto);

    public EmployeeDto mapToDto(Employee employee);

    public List<Employee> mapToEntities(List<EmployeeDto> employeeDtos);

    public List<EmployeeDto> mapToDtos(List<Employee> employees);

}
