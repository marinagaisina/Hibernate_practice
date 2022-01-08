package jpa.entitymodels;

import javax.persistence.*;

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
    private StudentDetails stud_Details;

//    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY,
//    cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
//    private List<jpa.entities.Course> sCourses;

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
        this.stud_Details = studDetails;
    }

//    public void setsCourses(List<jpa.entities.Course> sCourses) {
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
        return stud_Details;
    }

//    public List<jpa.entities.Course> getsCourses() {
//        return sCourses;
//    }

    @Override
    public String toString() {
        return "jpa.entities.Student{" +
                "sEmail='" + sEmail + '\'' +
                ", name='" + sName + '\'' +
                ", password='" + sPass + '\'' +
                '}';
    }
}
