package com.global.hr.repository;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    List<Employee> findBySalary(Double salary, String name);

    List<Employee> findByDepartment (Long deptId);

    List<Employee> findByNameContainingAndDepartmentNameContaining(String empName, String deptName);

    Long countByNameContainingAndDepartmentNameContaining(String empName, String deptName);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional()
    Long deleteByNameContainingAndDepartmentNameContaining(String empName, String deptName);

    // this is the JPQL
    @Query(value = "select emp from Employee emp where emp.name like :empName")
    List<Employee> filter(@Param("empName") String name);

    // this is the sql native
    @Query(value = "select * from hr_employees emp where emp.emp_name like :empName", nativeQuery = true)
    List<Employee> filterNative(@Param("empName") String name);

    List<Employee> findByDepartmentId(Long deptId);

    @Query(value = "select \n" +
            " (select count(*) from department) deptCount,\n" +
            " (select count(*) from hr_employees) empCount,\n" +
            " (select count(*) from sec_users) userCount",
            nativeQuery = true)
    HRStatisticProjection getHRStatistic();

//    @Query(value = "select emp from Employee emp join emp.department dept where dept.id = :deptId")
//    List<Employee> findByDepartment(Long deptId);

}
