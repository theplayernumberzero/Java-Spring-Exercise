package com.theplayernumberzero.springboot.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.theplayernumberzero.springboot.cruddemo.dao.EmployeeDAO;
import com.theplayernumberzero.springboot.cruddemo.entity.Employee;
import com.theplayernumberzero.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper){
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }
    //expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);

        if (employee == null){
            throw new RuntimeException("Employee id not found: " + employeeId);
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);  //because there is auto-increment set 0 or null

        Employee dbEmployee = employeeService.save(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee dbEmployee = employeeService.save(employee);

        return employee;
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload){

        Employee tempEmployee = employeeService.findById(employeeId);

        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        //user cant change id field
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("You cant modify id field");
        }

        Employee patchedEmployee = apply(patchPayload, tempEmployee);

        //save to the db
        Employee dbEmployee = employeeService.save(patchedEmployee);

        return dbEmployee;
    }

    private Employee apply(Map<String, Object> patchPayload, Employee tempEmployee) {
        //employee obj to json
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);
        //patchPayload map to json
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        //merge patch and object
        employeeNode.setAll(patchNode);

        //convert back to object
        return objectMapper.convertValue(employeeNode, Employee.class);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee = employeeService.findById(employeeId);

        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }
}
