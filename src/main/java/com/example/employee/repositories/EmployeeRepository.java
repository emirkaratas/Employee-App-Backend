package com.example.employee.repositories;

import com.example.employee.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRepository extends JpaRepository<Employee,Integer> { }