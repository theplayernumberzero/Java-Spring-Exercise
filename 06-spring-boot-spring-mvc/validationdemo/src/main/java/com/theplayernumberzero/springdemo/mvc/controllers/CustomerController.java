package com.theplayernumberzero.springdemo.mvc.controllers;

import com.theplayernumberzero.springdemo.mvc.models.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    //initBinder for trim Strings form data
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @GetMapping("/")
    //Model allow us to share information between controllers and viewpage
    public String showForm(Model theModel){
        theModel.addAttribute("customer", new Customer());

        return "customer-form";
    }

    @PostMapping("processForm")
    //Validation için ara katman
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult bindingResult){

        System.out.println("Binding result: " + bindingResult.toString());
        if (bindingResult.hasErrors()){
            return "customer-form";
        }else {
            return "customer-confirmation";
        }
    }
}
