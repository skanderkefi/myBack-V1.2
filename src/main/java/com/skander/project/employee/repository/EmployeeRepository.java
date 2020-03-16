package com.skander.project.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.skander.project.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Employee findByEmail(String email);
}
