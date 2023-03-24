package com.global.hr.controller;

import com.global.hr.entity.Employee;
import com.global.hr.entity.EmployeeResponse;
import com.global.hr.repository.EmployeeRepo;
import com.global.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public EmployeeResponse findById (@PathVariable Long id) {

        Employee emp = employeeService.findById(id);

        EmployeeResponse res = new EmployeeResponse();
        res.setId(emp.getId());
        res.setName(emp.getName());
        res.setDepartment(emp.getDepartment());
        res.setUser(emp.getUser());

        return res;
    }

    @GetMapping("/emp-dept")
    public List<Employee> findByNameAndDept(@RequestParam String empName,@RequestParam String deptName) {

        return employeeService.findByEmpAndDept(empName, deptName);
    }

    @GetMapping("/count-emp-dept")
    public ResponseEntity<Long> countByNameAndDept(@RequestParam String empName, @RequestParam String deptName) {

        return ResponseEntity.ok(employeeService.countByEmpAndDept(empName, deptName));
    }

    @DeleteMapping("/emp-dept")
    public ResponseEntity<Long> deleteByNameAndDept(@RequestParam String empName, @RequestParam String deptName) {

        return ResponseEntity.ok(employeeService.deleteByEmpAndDept(empName, deptName));
    }


    @GetMapping("")
    public List<Employee> findAll() {
        logger.info("Get");

        return employeeService.findAll();
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
