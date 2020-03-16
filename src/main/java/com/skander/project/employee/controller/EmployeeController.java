package com.skander.project.employee.controller;

import java.net.URI;   
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skander.project.employee.exception.*;
import com.skander.project.employee.model.Employee;
import com.skander.project.employee.model.Login;
import com.skander.project.employee.services.EmployeeServices;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeServices empServices;

	
	@GetMapping("/")
	public List<Employee> getAllEmployees() {
		return empServices.findAllEmployees();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException
			 {
		Employee employee = empServices.findEmployeeById(employeeId);
		return ResponseEntity.ok().body(employee);
	}

	@CrossOrigin
	@PostMapping("/")
	public  ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		empServices.createEmployee(employee);
	    return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}
	@PostMapping("/connect")
	public  ResponseEntity<Employee> verifyEmployee(@Valid @RequestBody Login login) {
		Employee emp = empServices.login(login);
		if(emp!=null) {
		if(emp.getMdp().equals(login.getMdp())) {
			return new ResponseEntity<>(emp, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} }else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		
		return new ResponseEntity<Employee>(empServices.editEmployee(employeeId, employeeDetails)
	    		, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		
		empServices.deleteEmployee(employeeId);;

		
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}
}
