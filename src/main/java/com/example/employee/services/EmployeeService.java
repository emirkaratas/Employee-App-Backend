package com.example.employee.services;

import com.example.employee.entities.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getOneEmployeeById(int id);

    Employee createOneEmployee(Employee employee);

    Employee updateOneEmployee(int id, Employee employee);

    void deleteOneEmployee(int id);
}
