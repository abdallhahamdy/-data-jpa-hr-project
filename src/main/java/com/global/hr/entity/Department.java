package com.global.hr.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_gen")
//    @SequenceGenerator(name = "department_gen", sequenceName = "department_seq", initialValue = 100)

//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "department_gen")
//    @TableGenerator(name = "department_gen", table = "department_seq", allocationSize = 1)
//
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "department_gen")
//    @TableGenerator(name = "department_gen", table = "department_seq", allocationSize = 1, initialValue = 20)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
