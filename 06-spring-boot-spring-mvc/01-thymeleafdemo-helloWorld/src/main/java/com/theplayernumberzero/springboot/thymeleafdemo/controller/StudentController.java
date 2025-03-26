package com.theplayernumberzero.springboot.thymeleafdemo.controller;

import com.theplayernumberzero.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${systems}")
    private List<String> systems;

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel){
        Student student = new Student();

        theModel.addAttribute("student", student);

        //add list of countries to the model
        theModel.addAttribute("countries" ,countries);

        //add list of languages to the model
        theModel.addAttribute("languages" ,languages);

        theModel.addAttribute("systems", systems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processFrom(@ModelAttribute("student") Student theStudent){

        System.out.println("The student name: " + theStudent.getFirstName() + " last name: " + theStudent.getLastName());

        return "student-confirmation";
    }
}
