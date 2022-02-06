package jpa.entitymodels;
import javax.persistence.*;

@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_generator")
    @SequenceGenerator(name="course_generator", sequenceName = "course_seq", initialValue = 11) //allocationSize = 100
    @Column(name="cId")
    private int cId;

    @Column(name="cName")
    private String cName;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="instructorId")
    private Instructor instructor;

    public Course(String cName, Instructor instructor) {
        this.cName = cName;
        this.instructor = instructor;
    }
    public Course(String cName) {
        this.cName = cName;
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

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    @Override
    public String toString() {
        return this.cId + ". " +
                this.cName + ", " + this.instructor;
    }
}
