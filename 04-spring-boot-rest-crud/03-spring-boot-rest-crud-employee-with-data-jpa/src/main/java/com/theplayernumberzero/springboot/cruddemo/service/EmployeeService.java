package com.theplayernumberzero.springboot.cruddemo.service;

import com.theplayernumberzero.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
