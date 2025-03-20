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
    private Coach theAnotherCoach;

    @Autowired
    public DemoController(@Qualifier("baseballCoach") Coach theCoach, @Qualifier("baseballCoach") Coach anotherCoach){  //baseball will be used because @primary
        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
        theAnotherCoach = anotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String checkIfSame(){
        return "Are they same: " + (myCoach == theAnotherCoach);
    }

}
