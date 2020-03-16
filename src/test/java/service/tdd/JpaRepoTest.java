package service.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skander.project.employee.model.Employee;
import com.skander.project.employee.repository.EmployeeRepository;

@DataJpaTest
public class JpaRepoTest {

	@Mock
	 private EmployeeRepository repository;
	
	@Test
    public void should_find_all_customers() {

        Iterable<Employee> emps = repository.findAll();

        int nOfEmp = 14;
        assertThat(emps).hasSize(nOfEmp);
    }
	
	@Test
	void whenSaved_thenFindsByName() {
	  repository.save(new Employee("firstName", "lastName", "email", "mdp"));
	  assertThat(repository.findByEmail("email")).isNotNull();
	}
	
	
	
	
	
}
