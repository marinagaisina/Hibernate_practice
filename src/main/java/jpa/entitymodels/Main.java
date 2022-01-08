package jpa.entitymodels;

import jpa.service.CourseService;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static CourseService courseService;
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MSMDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        System.out.println("Creating new student objects...");
        Student student1 = new Student("student1@gmail.com", "Student1 LastName1", "password1");
        Student student2 = new Student("student2@gmail.com", "Student2 LastName2", "password2");
        Student student3 = new Student("student3@yahoo.com", "Student3 LastName3", "password3");

        StudentDetails stud1Details = new StudentDetails("ksdfjsdlfkldjfkdjfkdjfkjd");
        StudentDetails stud2Details = new StudentDetails("llllllllllllllllll");
        StudentDetails stud3Details = new StudentDetails("oooooooooooo");

        System.out.println("Connecting Students and their details...");
        student1.setStudDetails(stud1Details);
        student2.setStudDetails(stud2Details);
        student3.setStudDetails(stud3Details);
        entityManager.getTransaction().commit();

        System.out.println("Saving the students and student details...");

        /* no need to persist the connected tables!
        entityManager.persist(stud1Details);
        entityManager.persist(stud2Details);
        entityManager.persist(stud3Details);
         */
        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);

        // find out the student's email: primary key:
        System.out.println("Saved students. jpa.entities.Student's primary key is: "+student1.getsEmail());
        System.out.println("\n----------------Retrieving (getting) the student's data from DB using entityManager.find():----------------------------");
        Student gettingStudent = entityManager.find(Student.class, student1.getsEmail());
        System.out.println(gettingStudent.toString());

        // Hibernate Query Language!
        entityManager.getTransaction().begin();
        System.out.println("\n----------------Retrieving (getting) the student's data from DB using createQuery():----------------------------");
        List<Student> theStudents = entityManager.createQuery("select a from Student a", Student.class).getResultList();
        entityManager.getTransaction().commit();
        displayStudents(theStudents);

        theStudents = entityManager.createQuery("select s from Student s where s.sEmail like '%@gmail.com'", Student.class).getResultList();
        displayStudents(theStudents);

        entityManager.getTransaction().begin();
        System.out.println("\n------------------Updating student1 using student1.setName():----------------------------------");
        student1.setsName("Student1update NoLastName");
        entityManager.getTransaction().commit();

        gettingStudent = entityManager.find(Student.class, student1.getsEmail()); // use the primary KEY!!
        System.out.println("Updated student name is : "+gettingStudent.getsName());

        System.out.println("\n------------------Updating all the students passwords using createQuery():-----------------------");
        entityManager.getTransaction().begin();
        String query = "update Student s set s.sPass=:passForAll";
        Query query1 = entityManager.createQuery(query);
        query1.setParameter("passForAll", "pass1234");
        query1.executeUpdate();
        int rows = query1.executeUpdate();
        entityManager.getTransaction().commit();

        System.out.println("\n------------------Getting updated list of students from DB----------------------");
        System.out.println("Affected rows"+rows);
        theStudents = entityManager.createQuery("select a from Student a", Student.class).getResultList();
        displayStudents(theStudents);
        Student student5 = entityManager.find(Student.class, student1.getsEmail());
        System.out.println(student5.toString());


        entityManager.getTransaction().begin();
        System.out.println("\n------------------Deleting a student using createQuery():-----------------------");
        entityManager.createQuery("delete from Student where sEmail='student1@gmail.com'").executeUpdate(); // WHY DOESN'T DELETE A CASCADED ROW???!



        System.out.println("\n------------------Deleting a student using delete():-----------------------");
        //entityManager.remove(student2);

        entityManager.getTransaction().commit();
        //displayStudents(theStudents);



        System.out.println("Done!");
        entityManager.close();
        entityManagerFactory.close();
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student student : theStudents) {
            System.out.println(student.toString());
        }
    }
    public static List<Student> getStudentsFromDB() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MSMDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> rootEntry = cq.from(Student.class);
        CriteriaQuery<Student> all = cq.select(rootEntry);

        TypedQuery<Student> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
 }
