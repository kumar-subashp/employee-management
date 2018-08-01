package com.employee.management.employeemanagement.service.impl;

import com.employee.management.employeemanagement.domain.Employee;
import com.employee.management.employeemanagement.repository.EmployeeDAO;
import com.employee.management.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public String create(Employee employee) {
        employeeDAO.save(employee);

        return employee.getEmpId();
    }

    @Override
    public void update(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    public void delete(String empId) {
        Employee employee = new Employee();
        employee.setEmpId(empId);
        employeeDAO.delete(employee);
    }

    @Override
    public Employee view(String empId) {
        Optional<Employee> employee = employeeDAO.findById(empId);
        return employee.get();
    }

    @Override
    public List<Employee> fetchAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(String empId) {
        return employeeDAO.findById(empId).get();
    }

    @Override
    public Employee findEmployeeByHighestSalary() {
        return employeeDAO.findTopByOrderBySalaryDesc();

    }
}
