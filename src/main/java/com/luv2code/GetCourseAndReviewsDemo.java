package com.luv2code;

import com.luv2code.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewsDemo {
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

            //start a transaction
            session.beginTransaction();

            //get the course
            int theId = 10;
            Course tempCourse = session.get(Course.class, theId);

            //print the course
            System.out.println(tempCourse);

            //print the course reviews
            System.out.println(tempCourse.getReviews());

            //commit transaction
            session.getTransaction().commit();


            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
