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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="instructorId")
    private Instructor instructor;

    public Course(String cName) {
        this.cName = cName;
        this.instructor = null;
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

    @Override
    public String toString() {
        return new StringBuilder().append(this.cId).append(". ")
                .append(this.cName).append(", ").append(this.instructor).toString();
    }
}
