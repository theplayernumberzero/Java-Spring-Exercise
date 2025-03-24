package com.theplayernumberzero.springboot.cruddemo.service;

import com.theplayernumberzero.springboot.cruddemo.dao.EmployeeRepository;
import com.theplayernumberzero.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    //service gona use DAO
    private EmployeeRepository employeeRepository;

    @Autowired  //@autowiried sayesinde bu interface implemente eden class getiriyor
    public EmployeeServiceImpl(EmployeeRepository employeeDAO){
        this.employeeRepository = employeeDAO;
    }
    @Override
    public List<Employee> findAll() {
        //delegate
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {

        Optional<Employee> result = employeeRepository.findById(id);

        Employee theEmployee = null;

        if(result.isPresent()){
            theEmployee = result.get();
        }else{
            throw new RuntimeException("Didnt find employee id - " + id);
        }

        return theEmployee;
    }

    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
