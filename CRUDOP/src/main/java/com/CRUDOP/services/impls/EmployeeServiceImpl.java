package com.CRUDOP.services.impls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRUDOP.controllers.EmployeeController;
import com.CRUDOP.entities.Employee;
import com.CRUDOP.exceptions.ResourceNotFoundException;
import com.CRUDOP.mapper.EmployeeMapper;
import com.CRUDOP.payloads.EmployeeDto;
import com.CRUDOP.repositories.EmployeeRepository;
import com.CRUDOP.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        System.out.println("inside createEmployee");
        Employee employee = employeeMapper.mapToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto mappedEmployeeDto = employeeMapper.mapToDto(savedEmployee);

        return mappedEmployeeDto;
    }

    @Override
    public EmployeeDto getOneEmployee(Long id) {
        System.out.println("inside getOneEmployee");
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found on Server for id : "+id));
        return employeeMapper.mapToDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        System.out.println("inside getAllEmployee");
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.mapToDtos(employees);
    }

    @Override
    public EmployeeDto updateOneEmployee(Long id, EmployeeDto employeeDto) {
        System.out.println("inside updateOneEmployee");
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
        employee.setAge(employeeDto.getAge());
        employee.setAddress(employeeDto.getAddress());
        employeeRepository.save(employee);
        EmployeeDto mappedEmployeeDto = employeeMapper.mapToDto(employee);
        return mappedEmployeeDto;
    }

    @Override
    public boolean deleteOneEmployee(Long id) {
        System.out.println("inside deleteOneEmployee");
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found on Server for id : "+id));
        if(employee!=null) {
            employeeRepository.delete(employee);
            return true;
        }

        return false;
    }
}
