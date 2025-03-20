package com.theplayernumberzero.springcoredemo.rest;

import com.theplayernumberzero.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //define private field for Depedency
    private Coach myCoach;

    @Autowired
    public DemoController(Coach theCoach){  //baseball will be used because @primary
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

}
