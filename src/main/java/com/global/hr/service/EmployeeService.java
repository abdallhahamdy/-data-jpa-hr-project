package com.global.hr.service;

import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentService departmentService;

    public Employee findById (Long id) {

        return employeeRepo.findById(id).orElseThrow();
    }

    public List<Employee> filter (String name) {

        return employeeRepo.filterNative(name);
    }

    public Employee insert (Employee emp) {

        if (emp.getDepartment() != null && emp.getDepartment().getId() != null) {
            emp.setDepartment(departmentService.findById(emp.getDepartment().getId()));
        }

        return employeeRepo.save(emp);
    }

    public Employee update (Employee emp) {

        Employee current = employeeRepo.findById(emp.getId()).get();

        current.setName(emp.getName());
        current.setSalary(emp.getSalary());
        current.setDepartment(emp.getDepartment());

        return employeeRepo.save(emp);
    }

    public List<Employee> findByDepartmentId(Long deptId) {
        return employeeRepo.findByDepartment(deptId);
    }

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }
}
