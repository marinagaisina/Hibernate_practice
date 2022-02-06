package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Instructor;
import jpa.entitymodels.Student;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService implements CourseDAO {
    private final EntityManager entityManager;

    public CourseService() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MSMDB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void close() {
        entityManager.close();
    }

    @Override
    public List<Course> getAllCourses() {

        List<Course> list = entityManager.createQuery("select c from Course c", Course.class).getResultList();

//        for (Course course : list) {
//            System.out.println(course.toString());
//        }
        return list;
    }

    @Override
    public Instructor getInstructor(String instructorName) {

        Query query = entityManager.createQuery("select i from Instructor i where i.name =:requiredName", Instructor.class)
                .setParameter("requiredName", instructorName);

        List<Instructor> list = entityManager.createQuery("select i from Instructor i where i.name =:requiredName", Instructor.class)
                .setParameter("requiredName", instructorName).getResultList();

        return (Instructor) query.getResultList().get(0);

/* ---------------------- using Criteria ! -----------------------------
        public Employee getEmployeeByEmail(String empEmail) {
            Employee e = (Employee)
            Criteria crit = sessionFactory.getCurrentSession().createCriteria(Employee.class);
            crit.add(Restrictions.eq("email", empEmail)).uniqueResult();
            return e;
        }
        Query query= sessionFactory.getCurrentSession().
        createQuery("from Category where name=:name");
        query.setParameter("name", name);
        Category category = (Category) query.uniqueResult();
 */
    }
}
