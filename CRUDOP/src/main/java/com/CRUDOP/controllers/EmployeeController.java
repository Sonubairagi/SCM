package com.CRUDOP.controllers;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUDOP.entities.Employee;
import com.CRUDOP.payloads.EmployeeDto;
import com.CRUDOP.repositories.EmployeeRepository;
import com.CRUDOP.services.EmployeeService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/v1/apis/employee/")
// @CrossOrigin(origins = "http://127.0.0.1:5500")
@CrossOrigin(origins = "http://localhost:  3000")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto){
        System.out.println("inside createEmployee");
        EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);
        System.out.println(savedEmployeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneEmployee(@PathVariable Long id) {
        System.out.println("inside getOneEmployee : "+id);
        EmployeeDto employeeDto = employeeService.getOneEmployee(id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllEmployee() {
        System.out.println("inside getAllEmployee");
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateOneEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        System.out.println("inside updateOneEmployee : "+id);
        EmployeeDto updatedEmployeeDto = employeeService.updateOneEmployee(id, employeeDto);
        return new ResponseEntity<>(updatedEmployeeDto, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteOneEmployee(@PathVariable Long id) {
        System.out.println("inside deleteOneEmployee : "+id);
        boolean status = employeeService.deleteOneEmployee(id);
        String response = status ? "Employee has been deleted!" : "error occured during deleting employee!";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
