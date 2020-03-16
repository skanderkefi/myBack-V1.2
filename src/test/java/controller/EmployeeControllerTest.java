package controller;

import static org.junit.Assert.assertTrue; 

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import com.skander.project.employee.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @Test
    public void testCreateEmployee() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
          createURLWithPort("/employees/"), HttpMethod.POST, entity, String.class);
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        assertTrue(actual.contains("/Employees/"));
    }    
    @Test
    public void testRetrieveEmployee() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
          createURLWithPort("/employees/4"), HttpMethod.GET, entity, String.class);
        String expected = "{\"id\":4,\"firstName\":\"kefi\",\"lastName\":\"skander\",\"email\":\"kefiskander99@gmail.com\",\"mdp\":\"sdqdsd\"}";
        JSONAssert.assertEquals(expected, response.getBody(),false);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
