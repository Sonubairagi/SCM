package com.CRUDOP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRUDOP.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
