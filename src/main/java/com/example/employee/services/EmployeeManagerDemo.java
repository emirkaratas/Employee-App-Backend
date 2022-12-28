package com.example.employee.services;

import com.example.employee.entities.Employee;
import com.example.employee.entities.exceptions.EmployeeNotFoundException;
import com.example.employee.repositories.EmployeeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository("mysql")
public class EmployeeManagerDemo implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeManagerDemo(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Override
    public Employee getOneEmployeeById(int id) {
        return employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
    }

    @Override
    public Employee createOneEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateOneEmployee(int id, Employee employee) {
        if (employee.getId()!=id){
            throw new RuntimeException("Please check the id!");
        }
        var entity = getOneEmployeeById(id);
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setSalary(employee.getSalary());
        return employeeRepository.save(entity);
    }

    @Override
    public void deleteOneEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
