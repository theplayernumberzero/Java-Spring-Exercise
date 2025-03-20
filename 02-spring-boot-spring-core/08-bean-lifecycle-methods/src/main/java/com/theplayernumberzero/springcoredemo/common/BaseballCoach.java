package com.theplayernumberzero.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BaseballCoach implements Coach{

    public BaseballCoach(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "It is baseball training time!!";
    }

    @PostConstruct
    public void myInitMethod(){
        System.out.println("You are in init method");
    }

    @PreDestroy
    public void myDestroyMethod(){
        System.out.println("You are in destroy method");
    }
}
