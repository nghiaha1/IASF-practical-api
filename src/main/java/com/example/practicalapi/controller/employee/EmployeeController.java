package com.example.practicalapi.controller.employee;

import com.example.practicalapi.entity.Employee;
import com.example.practicalapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(value = "*")
@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@RequestBody Employee obj) {
        return ResponseEntity.ok(employeeService.save(obj));
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        if (optionalEmployee.isPresent()) {
            ResponseEntity.ok(optionalEmployee.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee updatedObj) {
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existEmployee = optionalEmployee.get();
            existEmployee.setName(updatedObj.getName());
            existEmployee.setWage(updatedObj.getWage());
            employeeService.save(existEmployee);
            return ResponseEntity.ok(employeeService.save(existEmployee));
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeService.delete(id);
            ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}