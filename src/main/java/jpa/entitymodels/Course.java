package jpa.entitymodels;
import javax.persistence.*;

@Entity
@Table(name="course")
public class Course {
    @Id
    @Column(name="cId")
    private int cId;

    @Column(name="cName")
    private String cName;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name="instructorId")
    private Instructor instructor;

    public Course(int cId, String cName, Instructor instructor) {
        this.cId = cId;
        this.cName = cName;
        this.instructor = instructor;
    }

    public Course() {

    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
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
