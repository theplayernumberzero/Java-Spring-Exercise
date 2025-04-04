package com.theplayernumberzero.cruddemo.dao;

import com.theplayernumberzero.cruddemo.entity.Instructor;

public interface AppDao {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);
}
