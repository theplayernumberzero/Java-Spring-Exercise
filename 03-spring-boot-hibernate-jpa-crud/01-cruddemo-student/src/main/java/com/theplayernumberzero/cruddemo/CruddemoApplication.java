package com.theplayernumberzero.cruddemo;

import com.theplayernumberzero.cruddemo.dao.StudentDAO;
import com.theplayernumberzero.cruddemo.entity.Student;
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
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);
			//createMultipleStudents(studentDAO);

			//readStudent(studentDAO);

			//queryForStudents(studentDAO);

			//queryForStudentsByLastName(studentDAO);

			//updateStudent(studentDAO);

			//deleteStudent(studentDAO);

			deleteAllStudents(studentDAO);

		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int nums = studentDAO.deleteAll();

		System.out.println("Deleted row count: " + nums);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int id = 1;

		System.out.println("Deleting student id: " + id);
		studentDAO.delete(id);
	}

	private void updateStudent(StudentDAO studentDAO) {
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);

		Student myStudent = studentDAO.findById(studentId);

		System.out.println("Updating student");
		myStudent.setFirstName("Yigit");

		studentDAO.update(myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Kilic");

		for (Student student : students){
			System.out.println(student.toString());
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		System.out.println("Reading all student table..");

		List<Student> students = studentDAO.findAll();

		for (Student student : students){
			System.out.println(student.toString());
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Creating student obj..");

		Student tempStudent1 = new Student("Kezban", "Ayik", "kax@gmail.com");

		studentDAO.save(tempStudent1);

		System.out.println("Id of saved student " + tempStudent1.getFirstName() + ": " + tempStudent1.getId());

		//read data
		int theId = tempStudent1.getId();
		System.out.println("Retreiving student with id: " + theId);

		Student myStudent = studentDAO.findById(theId);
		System.out.println(myStudent.toString());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 student obj..");

		Student tempStudent1 = new Student("Bahadir", "Kilic", "bxk@gmail.com");
		Student tempStudent2 = new Student("Umut", "Ata", "uxa@gmail.com");
		Student tempStudent3 = new Student("Arda", "Kel", "axk@gmail.com");

		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		System.out.println("Id of saved student 1: " + tempStudent1.getId());
		System.out.println("Id of saved student 2: " + tempStudent2.getId());
		System.out.println("Id of saved student 3: " + tempStudent3.getId());
	}

	private void createStudent(StudentDAO studentDAO) {
		//create student obj
		System.out.println("Createing new student..");
		Student tempStudent = new Student("Bahadir", "Kilic", "bxk@gmail.com");

		//save student obj
		System.out.println("Saving new student");
		studentDAO.save(tempStudent);

		//display id of saved student
		System.out.println("Id of saved student: " + tempStudent.getId());
	}
}
