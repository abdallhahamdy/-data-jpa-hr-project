package com.global.hr.controller;

import com.global.hr.entity.Department;
import com.global.hr.entity.Employee;
import com.global.hr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {

        return departmentService.findById(id);
    }

    @PostMapping()
    public Department insert (@RequestBody Department dept) {

        return departmentService.insert(dept);
    }

    @PutMapping()
    public Department update (@RequestBody Department dept) {

        return departmentService.update(dept);
    }

    @GetMapping()
    public List<Department> findAll() {
        return departmentService.findAll();
    }
}
