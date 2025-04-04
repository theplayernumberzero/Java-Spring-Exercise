package com.theplayernumberzero.cruddemo;

import com.theplayernumberzero.cruddemo.dao.AppDao;
import com.theplayernumberzero.cruddemo.entity.Instructor;
import com.theplayernumberzero.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){

		return runner -> {
			createInstructor(appDao);

			//findInstructor(appDao);

			//deleteInstructor(appDao);
		};
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
