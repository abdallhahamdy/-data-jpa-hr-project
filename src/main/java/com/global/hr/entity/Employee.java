package com.global.hr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "hr_employees")
@Entity
@NamedQuery(name = "Employee.findBySalary", query = "select emp from Employee emp where emp.salary >= :salary" +
        " and name like :name")

@SqlResultSetMapping(
        name = "empMapping",
        entities = @EntityResult(
                entityClass = Employee.class,
                fields = {
                        @FieldResult(name = "id", column = "emp_id"),
                        @FieldResult(name = "name", column = "emp_name"),
                        @FieldResult(name = "salary", column = "salary")}))

@NamedNativeQuery(name = "findByDepartment", query = "select emp_id, emp_name, salary" +
        " from hr_employees where department_id = :deptId ", resultSetMapping = "empMapping")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "emp_name")
    private String name;

    private Double salary;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
