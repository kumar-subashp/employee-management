package com.employee.management.employeemanagement.service;

import com.employee.management.employeemanagement.domain.Employee;

import java.util.List;

public interface EmployeeService {

    String create(Employee employee);

    void update(Employee employee);

    void delete(String empId);

    Employee view(String empId);

    List<Employee> fetchAll();

    Employee findById(String empId);

    Employee findEmployeeByHighestSalary();
}
