package com.skander.project.employee.model;

import javax.persistence.Column;  
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String mdp;
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String email, String mdp) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mdp=mdp;
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "mdp", nullable = false)
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "email_address", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
