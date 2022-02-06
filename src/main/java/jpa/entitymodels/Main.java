package jpa.entitymodels;

import jpa.service.CourseService;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static CourseService courseService = new CourseService();

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MSMDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Creating new student details objects...");
        StudentDetails stud1Details = new StudentDetails();
        stud1Details.setStudentDetails("studentDetails1");
        entityManager.persist(stud1Details);

        StudentDetails stud2Details = new StudentDetails();
        stud2Details.setStudentDetails("studentDetails2");
        entityManager.persist(stud2Details);

        StudentDetails stud3Details = new StudentDetails();
        stud3Details.setStudentDetails("studentDetails3");
        entityManager.persist(stud3Details);

        System.out.println("Creating new student objects...");
        Student student1 = new Student("student1@gmail.com", "Student1 LastName1", "password1");
        Student student2 = new Student("student2@gmail.com", "Student2 LastName2", "password2");
        Student student3 = new Student("student3@yahoo.com", "Student3 LastName3", "password3");

        System.out.println("Connecting Students and their details...");
        student1.setStudDetails(stud1Details);
        student2.setStudDetails(stud2Details);
        student3.setStudDetails(stud3Details);

        System.out.println("Saving the students and student details...");
        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.getTransaction().commit();

        // find out the student's email: primary key:
        System.out.println("Saved students. jpa.entities.Student's primary key is: "+student1.getsEmail());
        System.out.println("\n----------------Retrieving (getting) the student's data from DB using entityManager.find():----------------------------");
        Student gettingStudent = entityManager.find(Student.class, student1.getsEmail());
        System.out.println(gettingStudent.toString());

        // Hibernate Query Language!
        System.out.println("\n----------------Retrieving (getting) the student's data from DB using createQuery():----------------------------");

        String queryGetStudents = "select a from Student a";
        List<Student> theStudents = entityManager.createQuery(queryGetStudents, Student.class).getResultList();
        // SECOND WAY:
        //theStudents = entityManager.createQuery("select a from Student a", Student.class).getResultList();
        displayStudents(theStudents);
        System.out.println("Getting a list of students with email like: '@gmail.com':");
        theStudents = entityManager.createQuery("select s from Student s where s.sEmail like '%@gmail.com'", Student.class).getResultList();
        displayStudents(theStudents);

        System.out.println("\n------------------Updating student1 using student1.setName():----------------------------------");
        entityManager.getTransaction().begin();
        student1.setsName("Student1update NoLastName");
        entityManager.getTransaction().commit();

        gettingStudent = entityManager.find(Student.class, student1.getsEmail()); // use the primary KEY!!
        System.out.println("Updated student name is : "+gettingStudent.getsName());

        System.out.println("\n------------------Updating password for an instructor with name like \"Odessa\" using createQuery():-----------------------");
        entityManager.getTransaction().begin();
        String queryUpdate = "update Instructor i set i.name=:newName where i.name like 'Odessa%'";
        Query query = entityManager.createQuery(queryUpdate);
        query.setParameter("newName", "NotOdessa");
        query.executeUpdate();
        int rows = query.executeUpdate();
        System.out.println("Affected rows in Instructor table"+rows);
        entityManager.getTransaction().commit();

        System.out.println("\n------------------Getting updated student1 from DB----------------------");
        Student student5 = entityManager.find(Student.class, student1.getsEmail());
        System.out.println(student5.toString());

        System.out.println("\n------------------Deleting a student1 using createQuery():-----------------------");
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Student s where s.sEmail='student1@gmail.com'").executeUpdate(); // WHY DOESN'T DELETE A CASCADED ROW???!
        entityManager.getTransaction().commit();

        System.out.println("\nChecking list of Students after deleting student1:");
        theStudents = entityManager.createQuery(queryGetStudents, Student.class).getResultList();
        displayStudents(theStudents);
        System.out.println("Check if cascaded row for student1 details was deleted too: ");
        String queryGetStudentDetails = "from StudentDetails ds";
        //query1 = entityManager.createQuery(query); SECOND WAY, WE CAN ADD PARAMETERS, ETC.
        List<StudentDetails> studentDetails = entityManager.createQuery(queryGetStudentDetails, StudentDetails.class).getResultList();
        displayStudentDetails(studentDetails);

        System.out.println("\n------------------Deleting a student2 and student3 using remove():-----------------------");
        entityManager.getTransaction().begin();

        entityManager.remove(student2);
        entityManager.remove(student3);

        entityManager.getTransaction().commit();
        System.out.println("\nChecking list of Students after deleting student2:");
        theStudents = entityManager.createQuery(queryGetStudents, Student.class).getResultList();
        displayStudents(theStudents);
        System.out.println("Check if cascaded rows for deleted students' details were deleted too: ");
        studentDetails = entityManager.createQuery(queryGetStudentDetails, StudentDetails.class).getResultList();
        displayStudentDetails(studentDetails);

        System.out.println("\n---------------------List of courses:------------------------------------------");
        List<Course> output = courseService.getAllCourses();
        for (Course course : output) {
            System.out.println(course.toString());
        }

        System.out.println("\n------------------Creating instructors:-----------------------");
        entityManager.getTransaction().begin();
        Instructor instructor1 = new Instructor();
        instructor1.setName("Marina");
        entityManager.persist(instructor1);

        Instructor instructor2 = new Instructor();
        instructor2.setName("John");
        entityManager.persist(instructor2);

        Course math = new Course("Math");
        Course history = new Course("History");
        Course programming = new Course("JavaProgramming");
        instructor1.addCourse(math);
        instructor1.addCourse(programming);
        instructor2.addCourse(history);



        entityManager.persist(history);
        entityManager.persist(math);
        entityManager.persist(programming);

        entityManager.getTransaction().commit();

        System.out.println("***************************************************************************************************************");
        Instructor instructorMarina = entityManager.find(Instructor.class, 1);
        System.out.println("Looking for instructor with Id=1: "+instructorMarina.toString());
        System.out.println("courses: "+instructorMarina.getListCourses());

        System.out.println(courseService.getInstructor("Marina").toString());

        System.out.println("Done!");
        courseService.close();
        entityManager.close();
        entityManagerFactory.close();
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student student : theStudents) {
            System.out.println(student.toString());
        }
    }
    private static void displayStudentDetails(List<StudentDetails> list) {
        for (StudentDetails details : list) {
            System.out.println(details.toString());
        }
    }

    public static List<Student> getStudentsFromDB() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MSMDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> rootEntry = cq.from(Student.class);
        CriteriaQuery<Student> all = cq.select(rootEntry);

        TypedQuery<Student> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
 }
