package service.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList; 
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skander.project.employee.repository.EmployeeRepository;
import com.skander.project.employee.services.EmployeeServices;
import com.skander.project.employee.model.Employee;
import com.skander.project.employee.repository.EmployeeRepository;


public class EmployeeServiceTest {

	 @Mock
	 private EmployeeRepository repository;
	@InjectMocks
	private EmployeeServices employeeService;
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		this.initMocks();
	}
	
	
	
	@Test
	public void should_get_list_employees_as_null()
	{
		// PostConstruct can not be executed automatically with Mock
		List<Employee>employees = employeeService.getItrEmployees();
		Assert.assertNull(employees);
	}
	@Test
	public void should_get_list_employees()
	{
		employeeService.createEmployee();
		List<Employee>employees = employeeService.getItrEmployees();
		Assert.assertNotNull(employees);
	}
	
	
	private void initMocks()
	{
		  List<Employee> employees = new ArrayList<Employee>();
		  employees.add(new Employee("firstName", "lastName", "email", "mdp"));
		  employees.add(new Employee("firstName", "lastName", "email", "mdp"));
		  employees.add(new Employee("firstName", "lastName", "email", "mdp"));
	        Mockito.when(repository.saveAll(Mockito.anyCollection())).thenReturn(employees);
	}
	
}
