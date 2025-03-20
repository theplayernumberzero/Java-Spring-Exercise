package com.theplayernumberzero.springcoredemo.common;

public class SwimCoach implements Coach{

    public SwimCoach(){
        System.out.println("In constructor: " + (getClass().getSimpleName()));
    }

    @Override
    public String getDailyWorkout() {
        return "Do your daily swim routine!!";
    }
}
