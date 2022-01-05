import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MSMDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        System.out.println("Creating new student object...");
        Student student1 = new Student("marinawhite3429@gmail.com", "Marina Gaisina", "pass");
        Student student2 = new Student("student2@gmail.com", "Student2 LastName", "pass");
        Student student3 = new Student("student3@yahoo.com", "Student3 LastName", "pass");
        System.out.println("Saving the student...");
        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);

        entityManager.getTransaction().commit();

        // find out the student's email: primary key:
        System.out.println("Saved student. Student's primary key is: "+student1.getEmail());
        System.out.println("Getting the student's data from DB:");
        Student gettingStudent = entityManager.find(Student.class, student1.getEmail());
        System.out.println(gettingStudent.toString());
        // Hibernate Query Language!


        System.out.println("Done!");
        entityManager.close();
        entityManagerFactory.close();
    }
}
