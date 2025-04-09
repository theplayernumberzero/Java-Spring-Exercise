package com.theplayernumberzero.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDaoImpl implements MembershipDao{


    @Override
    public void addMember() {
        System.out.println(getClass() + " : DOING MY DB WORK: ADDING A MEMBERSHIP ACCOUNT");
    }
}
