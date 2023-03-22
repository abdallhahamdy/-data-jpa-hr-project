package com.global.hr.repository;

import com.global.hr.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);

    // this is the JPQL
    @Query(value = "select emp from Employee emp where emp.name like :empName")
    List<Employee> filter(@Param("empName") String name);

    // this is the sql native
    @Query(value = "select * from employee emp where emp.name like :empName", nativeQuery = true)
    List<Employee> filterNative(@Param("empName") String name);

    List<Employee> findByDepartmentId(Long deptId);

    @Query(value = "select emp from Employee emp join emp.department dept where dept.id = :deptId")
    List<Employee> findByDepartment(Long deptId);

}
