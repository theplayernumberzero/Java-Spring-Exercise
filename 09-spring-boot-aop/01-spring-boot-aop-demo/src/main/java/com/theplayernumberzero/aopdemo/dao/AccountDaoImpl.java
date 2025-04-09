package com.theplayernumberzero.aopdemo.dao;

import com.theplayernumberzero.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao{

    @Override
    public void addAccount(Account account) {
        System.out.println(getClass() + " : DOING MY DB WORK: ADDING AN ACCOUNT");
    }
}
