package com.employee.management.employeemanagement.controller;

import com.employee.management.employeemanagement.domain.Employee;
import com.employee.management.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Employee employee) {

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{ssn}").buildAndExpand(employeeService.create(employee)).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/employees", method = RequestMethod.PUT)
    public void update(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> list(@RequestParam(value = "salary", required = false) String salary) {
        if ("MAX".equalsIgnoreCase(salary)) {
            return Arrays.asList(employeeService.findEmployeeByHighestSalary());
        }
        return employeeService.fetchAll();
    }

    @RequestMapping(value = "/employees/{empId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable String empId) {

        employeeService.delete(empId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
    }

    @RequestMapping(value = "/employees/{empId}", method = RequestMethod.GET)
    public Employee view(@PathVariable String empId) {
        return employeeService.view(empId);
    }
}
