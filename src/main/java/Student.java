import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* ------  Custom key generation:----------
  1. create implementation of org.hibernate.id.IdentifierGenerator (it is an interface);
  2. override the method: public Serializable generate(...) )

  ------- Auto generation: -------------
  use annotation: @GeneratedValue(strategy=GenerationType.IDENTITY)

  -------- Changing a starting point for an auto-increment:---------------
  in your DB:
  alter database_name.table table_name auto_increment = 1000;
  - will start id's from 1000.
*/

@Entity
@Table(name="student")
public class Student {
    @Id
    @Column(name="email")   // annotation @Column(...) does not work if you do not have it in your database yet
    private String sEmail;
    @Column(name="name")
    private String sName;
    @Column(name = "password")
    private String sPass;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studDetails")
    private StudentDetails studDetails;

//    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
//    private List<Course> sCourses = new ArrayList<>();

//    public void setsCourses(List<Course> sCourses) {    //???
//        this.sCourses = sCourses;
//    }
//
//    public List<Course> getsCourses() {
//        return sCourses;
//    }

    public Student() {
//        this.sEmail = "";
//        this.sName = "";
//        this.sPass = "";
        //this.sCourses = new ArrayList<>();
    }

    public Student(String email, String name, String password) {
        this.sEmail = email;
        this.sName = name;
        this.sPass = password;
        //this.sCourses = new ArrayList<>();
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    public void setStudDetails(StudentDetails studDetails) {
        this.studDetails = studDetails;
    }

//    public void setsCourses(List<Course> sCourses) {
//        this.sCourses = sCourses;
//    }

    public String getsEmail() {
        return sEmail;
    }

    public String getsName() {
        return sName;
    }

    public String getsPass() {
        return sPass;
    }

    public StudentDetails getStudDetails() {
        return studDetails;
    }

//    public List<Course> getsCourses() {
//        return sCourses;
//    }

    @Override
    public String toString() {
        return "Student{" +
                "sEmail='" + sEmail + '\'' +
                ", name='" + sName + '\'' +
                ", password='" + sPass + '\'' +
                '}';
    }
}
