package com.employee.management.employeemanagement;

import com.employee.management.employeemanagement.domain.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeManagementApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.JVM)
public class EmployeeManagementApplicationTests {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void addEmployee() {
        Employee employee = buildEmployee();

        HttpEntity<Employee> entity = new HttpEntity<Employee>(employee, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/employees"),
                HttpMethod.POST, entity, String.class);

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        assertTrue(actual.contains(employee.getEmpId()));
    }

    @Test
    public void getAllEmployees() throws IOException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/employees"),
                HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Employee> serviceResponse = mapper.readValue(response.getBody(), List.class);
        assertEquals(1, serviceResponse.size());

    }

    private Employee buildEmployee() {
        Employee employee = new Employee();
        employee.setEmpId("nirani");
        employee.setJobTitleName("Developer");
        employee.setFirstName("Neil");
        employee.setLastName("Irani");
        employee.setRegion("CA");
        employee.setEmailAddress("neilrirani@gmail.com");
        employee.setSalary(200L);
        return employee;
    }

    @Test
    public void getEmployee() throws IOException {
        Employee employee = buildEmployee();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/employees/" + employee.getEmpId()),
                HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();

        Employee serviceResponse = mapper.readValue(response.getBody(), Employee.class);

        assertEquals(employee.getEmpId(), serviceResponse.getEmpId());
    }

    @Test
    public void deleteEmployee() {
        Employee employee = buildEmployee();

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/employees/" + employee.getEmpId()),
                HttpMethod.DELETE, entity, String.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
