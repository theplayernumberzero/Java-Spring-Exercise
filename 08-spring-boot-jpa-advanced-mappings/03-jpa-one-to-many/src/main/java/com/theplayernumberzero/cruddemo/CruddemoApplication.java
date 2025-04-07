package com.theplayernumberzero.cruddemo;

import com.theplayernumberzero.cruddemo.dao.AppDao;
import com.theplayernumberzero.cruddemo.entity.Course;
import com.theplayernumberzero.cruddemo.entity.Instructor;
import com.theplayernumberzero.cruddemo.entity.InstructorDetail;
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
			//createInstructor(appDao);

			//findInstructor(appDao);

			//deleteInstructor(appDao);

			//findInstructorDetail(appDao);

			//deleteInstructorDetail(appDao);

			//createInstructorWithCourse(appDao);

			//findInstructorWithCourses(appDao);

			findCoursesForInstructor(appDao);
		};
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
