package com.theplayernumberzero.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    //add advices for logging. Let's start with @before
    @Before("execution(public void addAccount())")
    public void beforeAddAcoountAdvice(){
        System.out.println("\nExecuting @before advice on addAccount()");
    }

}
