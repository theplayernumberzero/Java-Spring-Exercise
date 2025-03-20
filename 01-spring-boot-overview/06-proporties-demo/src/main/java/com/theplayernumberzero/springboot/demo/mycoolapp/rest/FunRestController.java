package com.theplayernumberzero.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    //inject proporties

    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    @GetMapping("/teaminfo")
    public String showTeamInfo(){
        return "Coach: " + coachName + " ,Team: " + teamName;
    }

    @GetMapping("/")
    public String sayHello(){
        return "Hello World";
    }

    //New endpoint
    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Run 5km daily";
    }

    @GetMapping("/fortune")
    public String getDailyFortune(){
        return "Today is your lucky day";
    }
}
