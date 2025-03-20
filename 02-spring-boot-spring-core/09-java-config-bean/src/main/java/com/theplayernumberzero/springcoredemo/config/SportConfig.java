package com.theplayernumberzero.springcoredemo.config;

import com.theplayernumberzero.springcoredemo.common.Coach;
import com.theplayernumberzero.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean   //bean id = swimCoach
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
