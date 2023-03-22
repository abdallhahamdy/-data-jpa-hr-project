package com.global.hr.service;

import com.global.hr.entity.Department;
import com.global.hr.entity.Employee;
import com.global.hr.repository.DepartmentRepo;
import com.global.hr.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    public Department findById (Long id) {

        return departmentRepo.findById(id).orElseThrow();
    }

    public Department insert (Department dept) {

        return departmentRepo.save(dept);
    }

    public Department update (Department dept) {

        Department current = departmentRepo.findById(dept.getId()).orElseThrow();

        current.setName(dept.getName());

        return departmentRepo.save(dept);
    }

    public List<Department> findAll() {
        return departmentRepo.findAll();
    }
}
