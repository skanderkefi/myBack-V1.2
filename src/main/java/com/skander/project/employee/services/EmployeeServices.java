package com.skander.project.employee.services;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.skander.project.employee.model.Login;
import java.util.ArrayList;
import java.util.List;

import com.skander.project.employee.exception.ResourceNotFoundException;
import com.skander.project.employee.model.Employee;
import com.skander.project.employee.repository.EmployeeRepository;


@Service
public class EmployeeServices {

	   List<Employee> itrEmployees= null;
	@Autowired
	EmployeeRepository empRep;
	
	public List<Employee> findAllEmployees() {
		return empRep.findAll();
	}
	
	
	public List<Employee> getItrEmployees() {
		return itrEmployees;
	}
	public void setItrStudents(List<Employee> itrEmployees) {
		this.itrEmployees = itrEmployees;
	}
	
	
	public Employee findEmployeeById(Long id) throws ResourceNotFoundException {
		return empRep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
	}
	
	public Employee login(Login login) {
		return empRep.findByEmail(login.getEmail());
	}
	
	public List<Employee> createEmployee() {
        List<Employee> employees = new ArrayList<Employee>();
        List<Employee> savedEmployees = new ArrayList<Employee>();
        employees.add(new Employee("firstName", "lastName", "email", "mdp"));
        employees.add(new Employee("firstName1", "lastName1", "email1", "mdp1"));
        employees.add(new Employee("firstName2", "lastName2", "email2", "mdp2"));

        this.itrEmployees=empRep.saveAll(employees);
        itrEmployees.forEach(savedEmployees::add);
        return itrEmployees;
    }
	
	
	public void createEmployee(Employee employee) {
		empRep.save(employee);
	}
	
	public Employee editEmployee(Long id, Employee editEmp) throws ResourceNotFoundException {
		
		Employee employee = empRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

		employee.setEmail(editEmp.getEmail());
		employee.setLastName(editEmp.getLastName());
		employee.setFirstName(editEmp.getFirstName());
		employee.setMdp(editEmp.getMdp());

		final Employee updatedEmployee = empRep.save(employee);
		return updatedEmployee;
	}
	
	public void deleteEmployee(Long id) throws ResourceNotFoundException {
		Employee employee = empRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

		empRep.delete(employee);
	}
	
	
	
	
	
	
	
	
	
	
}
