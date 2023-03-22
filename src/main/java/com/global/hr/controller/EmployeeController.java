package com.global.hr.controller;

import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;
import com.global.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRepo employeeRepo;

    @GetMapping("/{id}")
    public Employee findById (@PathVariable Long id) {

        return employeeService.findById(id);
    }

    @GetMapping("")
    public List<Employee> findAll() {
        logger.info("Get");

        return employeeRepo.findAll();
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

    @GetMapping("/department/{deptId}")
    public List<Employee> findByDepartment(@PathVariable Long deptId) {
        return employeeService.findByDepartmentId(deptId);
    }

}
