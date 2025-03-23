package com.theplayernumberzero.springboot.cruddemo.dao;

import com.theplayernumberzero.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
