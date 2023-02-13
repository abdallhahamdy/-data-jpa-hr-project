package com.global.hr.controller;

import com.global.hr.entity.Employee;
import com.global.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee findById (@PathVariable Long id) {

        return employeeService.findById(id);
    }

    @GetMapping("/filter")
    public List<Employee> findByName (@PathVariable String name) {

        return employeeService.filter(name);
    }

    @PostMapping()
    public Long insert (@RequestBody Employee emp) {
        Employee inserted = employeeService.insert(emp);
        return inserted.getId();
    }

    @PutMapping()
    public Employee update (@RequestBody Employee emp) {

        return employeeService.update(emp);
    }

}
