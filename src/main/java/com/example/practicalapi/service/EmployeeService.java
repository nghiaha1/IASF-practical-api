package com.example.practicalapi.service;

import com.example.practicalapi.entity.Employee;
import com.example.practicalapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee obj) {
        return employeeRepository.save(obj);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
