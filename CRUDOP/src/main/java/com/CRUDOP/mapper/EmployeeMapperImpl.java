package com.CRUDOP.mapper;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.CRUDOP.entities.Employee;
import com.CRUDOP.payloads.EmployeeDto;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee mapToEntity(EmployeeDto employeeDto) {

        Employee employee = new Employee(employeeDto.getId(), employeeDto.getName(), employeeDto.getSalary(), employeeDto.getAge(), employeeDto.getAddress());

        return employee;
    }

    @Override
    public EmployeeDto mapToDto(Employee employee) {

        EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getName(), employee.getSalary(), employee.getAge(), employee.getAddress());

        return employeeDto;
    }
    
    @Override
    public List<Employee> mapToEntities(List<EmployeeDto> employeeDtos) {
        return employeeDtos.stream().map(e -> mapToEntity(e)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> mapToDtos(List<Employee> employees) {
        return employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
    }

}
