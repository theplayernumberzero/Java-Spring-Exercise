package com.theplayernumberzero.cruddemo.dao;

import com.theplayernumberzero.cruddemo.entity.Course;
import com.theplayernumberzero.cruddemo.entity.Instructor;
import com.theplayernumberzero.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao{

    private EntityManager entityManager;

    @Autowired
    public AppDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {

        Instructor tempInstructor = findInstructorById(id);

        //get courses

        List<Course> courses = tempInstructor.getCourses();

        //delete instructor associated with courses

        for(Course tempCourse : courses){
            tempCourse.setInstructor(null);
        }

        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    public void deleteInstructorDetailById(int id) {
        InstructorDetail tempInstructorDetail = findInstructorDetailById(id);

        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        //Create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor_id = :data" , Course.class
        );

        query.setParameter("data", id);

        //execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "SELECT i FROM Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail WHERE i.id = :id", Instructor.class
        );

        query.setParameter("id", id);

        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        //Thanks to cascade, will save course and review
        entityManager.persist(theCourse);

    }

    @Override
    public Course findCourseAndReviewsById(int id) {
        //c.reviews ile tablo değil entity class kast edilir. Yani javadaki nesneyi sorguluyoruz.
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM course c JOIN FETCH c.reviews WHERE c.id = :id" , Course.class
        );

        query.setParameter("id", id);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public void deleteCourseById(int id) {
        Course course = findCourseAndReviewsById(id);
        entityManager.remove(course);
    }


}
