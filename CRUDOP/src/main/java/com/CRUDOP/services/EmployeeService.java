package com.CRUDOP.services;

import java.util.List;

import com.CRUDOP.payloads.EmployeeDto;

public interface EmployeeService {

    public EmployeeDto createEmployee(EmployeeDto employeeDto);

    public EmployeeDto getOneEmployee(Long id);

    public List<EmployeeDto> getAllEmployee();

    public EmployeeDto updateOneEmployee(Long id, EmployeeDto employeeDto);

    public boolean deleteOneEmployee(Long id);

}
