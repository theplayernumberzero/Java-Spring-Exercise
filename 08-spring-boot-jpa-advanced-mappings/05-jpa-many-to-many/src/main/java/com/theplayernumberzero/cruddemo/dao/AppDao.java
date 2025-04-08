package com.theplayernumberzero.cruddemo.dao;

import com.theplayernumberzero.cruddemo.entity.Course;
import com.theplayernumberzero.cruddemo.entity.Instructor;
import com.theplayernumberzero.cruddemo.entity.InstructorDetail;
import com.theplayernumberzero.cruddemo.entity.Student;

import java.util.List;

public interface AppDao {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);

    void save(Course theCourse);

    Course findCourseAndReviewsById(int id);

    void deleteCourseById(int id);

    Course findCourseAndStudentByCourseId(int id);

    void updateStudent(Student student);

    Student findStudentAndCoursesByCourseId(int id);

    void deleteStudentById(int id);
}
