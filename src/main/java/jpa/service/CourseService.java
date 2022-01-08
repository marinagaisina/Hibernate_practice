package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Instructor;
import jpa.entitymodels.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService implements CourseDAO {

    @Override
    public List<Course> getAllCourses() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MSMDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        List<Course> list = entityManager.createQuery("select c from Course c", Course.class).getResultList();

        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();

//        for (Course course : list) {
//            System.out.println(course.toString());
//        }
        return list;
    }

    @Override
    public Instructor getInstructor(String instructorName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MSMDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Query query = entityManager.createQuery("select i from Instructor i where i.name =:requiredName");
        query.setParameter("requiredName", instructorName);

        return (Instructor) query.getResultList().get(0);
    }
}
