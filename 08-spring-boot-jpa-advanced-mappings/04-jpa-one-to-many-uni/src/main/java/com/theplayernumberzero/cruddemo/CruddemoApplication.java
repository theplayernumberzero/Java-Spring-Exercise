package com.theplayernumberzero.cruddemo;

import com.theplayernumberzero.cruddemo.dao.AppDao;
import com.theplayernumberzero.cruddemo.entity.Course;
import com.theplayernumberzero.cruddemo.entity.Instructor;
import com.theplayernumberzero.cruddemo.entity.InstructorDetail;
import com.theplayernumberzero.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){

		return runner -> {
			//createCourseAndReviews(appDao);

			//retrieveCourseAndReviews(appDao);

			deleteCourseAndReviews(appDao);
		};
	}

	private void deleteCourseAndReviews(AppDao appDao) {
		int id = 1;

		appDao.deleteCourseById(id);
	}

	private void retrieveCourseAndReviews(AppDao appDao) {
		int id = 1;

		Course course = appDao.findCourseAndReviewsById(id);

		System.out.println(course);
		System.out.println(course.getReviews());
	}

	private void createCourseAndReviews(AppDao appDao) {
		//Create course
		Course theCourse = new Course("Math");

		//add some review
		Review theReview1 = new Review("I love this course");
		Review theReview2 = new Review("I hate this course");

		//save the course
		theCourse.add(theReview1);
		theCourse.add(theReview2);

		//thanks to cascade course and reviews will be saved database
		appDao.save(theCourse);

		System.out.println(theCourse);
		System.out.println(theCourse.getReviews());
	}

	private void updateInstructor(AppDao appDao) {
		int id=1;

		Instructor instructor = appDao.findInstructorById(id);

		instructor.setEmail("change@gmail.com");

		appDao.update(instructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
		int id = 1;

		Instructor instructor = appDao.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: " + instructor);

		System.out.println("Related courses: " + instructor.getCourses());

		System.out.println("Related courses: " + instructor.getInstructorDetail());
	}

	private void findCoursesForInstructor(AppDao appDao) {
		int id=1;
		Instructor tempInstructor = appDao.findInstructorById(id);

		System.out.println("Instructor: " + tempInstructor);

		//find courses for instructor

		List<Course> courses = appDao.findCoursesByInstructorId(id);

		//associate the object
		tempInstructor.setCourses(courses);
	}

	private void findInstructorWithCourses(AppDao appDao) {
		int id=1;
		Instructor tempInstructor = appDao.findInstructorById(id);

		System.out.println("Instructor: " + tempInstructor);

		//For this line work we have to use fetch type EAGER
		System.out.println("Related courses: " + tempInstructor.getCourses());
	}

	private void createInstructorWithCourse(AppDao appDao) {

		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@luv2code.com");

		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.google.com", "art");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//create some courses
		Course tempCourse1 = new Course("Math");
		Course tempCourse2 = new Course("Biology");

		//add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		appDao.save(tempInstructor);
	}

	private void deleteInstructorDetail(AppDao appDao) {
		int id = 1;

		System.out.println("Deleting id: " + id);

		appDao.deleteInstructorDetailById(id);

		System.out.println("Deleted!!");
	}

	private void findInstructorDetail(AppDao appDao) {
		int id = 5;

		InstructorDetail tempInstructorDetail = appDao.findInstructorDetailById(id);

		System.out.println("The Instructor (thanks to relationship): " + tempInstructorDetail.getInstructor());

		System.out.println("Instructor Detail: " + tempInstructorDetail);
	}

	private void deleteInstructor(AppDao appDao) {
		int id = 3;

		System.out.println("Deleting id: " + id);

		appDao.deleteInstructorById(id);

		System.out.println("Deleted!!");
	}

	private void findInstructor(AppDao appDao) {

		int id = 3;
		System.out.println("Finding id: " + id);

		Instructor tempInstructor = appDao.findInstructorById(id);

		System.out.println("The Instructor: " + tempInstructor.toString());

		System.out.println("Instructor Detail: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDao appDao) {

		/*
		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@luv2code.com");

		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.google.com", "art");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Instructor saving to database: " +tempInstructor.toString());

		appDao.save(tempInstructor);

		System.out.println("Saving done!!!"); */

		Instructor tempInstructor =
				new Instructor("bahadir@luv2code.com", "Kilic", "Umut");

		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.google.com", "art");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Instructor saving to database: " +tempInstructor.toString());

		appDao.save(tempInstructor);

		System.out.println("Saving done!!!");
	}
}
