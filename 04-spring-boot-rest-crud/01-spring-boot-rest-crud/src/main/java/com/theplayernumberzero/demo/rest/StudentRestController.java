package com.theplayernumberzero.demo.rest;

import com.theplayernumberzero.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    //load data only once
    private List<Student> students;

    @PostConstruct
    public void loadData(){     //only load data once
        students = new ArrayList<>();

        students.add(new Student("Bahadir", "Kilic"));
        students.add(new Student("Fatih", "Terim"));
        students.add(new Student("Arda", "Güler"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    //path variable
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        //check studentId
        if(studentId >= students.size() || studentId < 0){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return students.get(studentId);
    }


}
