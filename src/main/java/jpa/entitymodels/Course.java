package jpa.entitymodels;
import javax.persistence.*;

@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cId")
    private int cId;

    @Column(name="cName")
    private String cName;

    @ManyToOne
    @JoinColumn(name="instructorId")
    private Instructor instructor;

    public Course(String cName, Instructor instructor) {
        this.cName = cName;
        this.instructor = instructor;
    }

    public Course() {

    }

    public int getcId() {
        return cId;
    }

    public String getcName() {
        return cName;
    }

//    public jpa.entities.Student getStudent() {
//        return student;
//    }

    public void setcName(String cName) {
        this.cName = cName;
    }

//    public void setStudent(jpa.entities.Student student) {
//        this.student = student;
//    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Instructor getInstructor() {
        return instructor;
    }
}
