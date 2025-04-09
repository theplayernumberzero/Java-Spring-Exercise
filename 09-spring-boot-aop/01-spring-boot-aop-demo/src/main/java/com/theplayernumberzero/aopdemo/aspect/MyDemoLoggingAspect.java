package com.theplayernumberzero.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    //add advices for logging. Let's start with @before
    @Before("execution(public void com.theplayernumberzero.aopdemo.dao.AccountDao.addAccount(com.theplayernumberzero.aopdemo.Account))")
    public void beforeAddAccountAdvice(){
        System.out.println("\nExecuting @before advice on add(Account)");
    }

    @Before("execution(public void add*(..))")
    public void beforeAddAccountAdvice2(){
        System.out.println("\nExecuting @before advice on add*()");
    }

}
