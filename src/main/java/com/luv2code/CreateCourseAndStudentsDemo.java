package com.luv2code;

import com.luv2code.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (factory; Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Course tempCourse = new Course("Pacman - How To Score One Million Points.");

            //save the student
            System.out.println("\nSaving the course ...");
            session.save(tempCourse);
            System.out.println("Saved the course: " + tempCourse);

            //create the students
            Student tempStudent1 = new Student("John", "Dow", "john@mail.com");
            Student tempStudent2 = new Student("John", "Wick", "boogerman@mail.com");

            //add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            //save the students
            System.out.println("\nSaving students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved students: " + tempCourse.getStudents());

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
