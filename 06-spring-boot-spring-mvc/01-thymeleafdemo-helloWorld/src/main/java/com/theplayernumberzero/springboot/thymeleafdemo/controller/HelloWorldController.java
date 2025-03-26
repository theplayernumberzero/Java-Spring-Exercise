package com.theplayernumberzero.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    //controller with read form data and add this data to model
    @RequestMapping("/processFormV2")
    public String processFormV2(HttpServletRequest request, Model theModel){

        //read request parameter from html form
        String name = request.getParameter("studentName");

        name = name.toUpperCase();

        theModel.addAttribute("message", name);

        return "helloworld";
    }

    @PostMapping("/processFormV3")
    public String processFormV3(@RequestParam("studentName") String name, Model theModel){

        String theName = name.toUpperCase();

        theModel.addAttribute("message", theName);

        return "helloworld";
    }
}
