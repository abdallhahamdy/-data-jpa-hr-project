package com.global.hr.service;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Department;
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

    public List<Employee> findByEmpAndDept(String empName, String deptName) {

        return employeeRepo.findByNameContainingAndDepartmentNameContaining(empName, deptName);
    }

    public Long countByEmpAndDept(String empName, String deptName) {

        return employeeRepo.countByNameContainingAndDepartmentNameContaining(empName, deptName);
    }

    public Long deleteByEmpAndDept(String empName, String deptName) {

        return employeeRepo.deleteByNameContainingAndDepartmentNameContaining(empName, deptName);
    }

    public List<Employee> filter (String name) {

        return employeeRepo.filterNative(name);
    }

    public Employee insert (Employee emp) {

        if (emp.getDepartment() != null && emp.getDepartment().getId() != null) {

            Department dept = departmentService.findById(emp.getDepartment().getId());
            dept.setName(emp.getDepartment().getName());

            emp.setDepartment(dept);

        }

        return employeeRepo.save(emp);
    }

    public Employee update (Employee emp) {

        Employee current = employeeRepo.findById(emp.getId()).orElseThrow();

        current.setName(emp.getName());
        current.setSalary(emp.getSalary());
        current.setDepartment(emp.getDepartment());

        return employeeRepo.save(current);
    }

    public List<Employee> findByDepartmentId(Long deptId) {
        return employeeRepo.findByDepartment(deptId);
    }

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public List<Employee> findBySalary(Double salary, String name) { return employeeRepo.findBySalary(salary, name); }

    public HRStatisticProjection getHRStatistic () {

        return employeeRepo.getHRStatistic();
    }
}
