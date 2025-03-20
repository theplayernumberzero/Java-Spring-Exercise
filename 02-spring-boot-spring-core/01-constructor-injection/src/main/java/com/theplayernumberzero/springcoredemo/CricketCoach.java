package com.theplayernumberzero.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "50 push up every morning!!!";
    }
}
