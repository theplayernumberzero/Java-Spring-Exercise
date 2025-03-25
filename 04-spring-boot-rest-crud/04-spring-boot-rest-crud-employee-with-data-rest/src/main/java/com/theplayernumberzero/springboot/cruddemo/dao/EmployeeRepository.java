package com.theplayernumberzero.springboot.cruddemo.dao;

import com.theplayernumberzero.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
