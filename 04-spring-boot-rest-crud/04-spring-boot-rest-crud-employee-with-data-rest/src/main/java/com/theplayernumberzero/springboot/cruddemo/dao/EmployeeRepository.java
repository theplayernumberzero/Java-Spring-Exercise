package com.theplayernumberzero.springboot.cruddemo.dao;

import com.theplayernumberzero.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
