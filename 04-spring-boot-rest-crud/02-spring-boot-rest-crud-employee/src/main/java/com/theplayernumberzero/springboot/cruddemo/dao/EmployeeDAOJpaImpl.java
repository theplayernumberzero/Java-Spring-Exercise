package com.theplayernumberzero.springboot.cruddemo.dao;

import com.theplayernumberzero.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository     //For DI
public class EmployeeDAOJpaImpl implements EmployeeDAO{
    //define field for entity manager
    private final EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        //Create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        //execute query and get result
        List<Employee> employees = theQuery.getResultList();

        //return the result
        return employees;
    }

    @Override
    public Employee findById(int id) {
        //Hibernate create query behind the scene
        Employee theEmployee = entityManager.find(Employee.class, id);

        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        Employee employee = entityManager.merge(theEmployee);

        return employee;
    }

    @Override
    public void deleteById(int theId) {
        Employee theEmployee = entityManager.find(Employee.class, theId);

        entityManager.remove(theEmployee);
    }
}
