package jpa.entitymodels;

import javax.persistence.*;

@Entity
@Table(name = "studentdetails")
public class StudentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stud_details_gen")
    @SequenceGenerator(name="stud_details_gen", sequenceName = "stud_details_seq", initialValue = 10) //allocationSize = 100
    @Column(name="studDetailsId")
    private int studDetailsId;
    @Column(name="studentDetails")
    private String studentDetails;
    @OneToOne(mappedBy = "stud_Details",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    //not "ALL", we won't delete jpa.entities.Instructor instance once we delete InstructorDetails
    //in table student, we have set a reference to the instructor's details to "null" and then delete instructorDetails without deleting instructor instance
    private Student student;

//    public StudentDetails(int id, String studentDetails) {
//        this.studDetailsId = id;
//        this.studentDetails = studentDetails;
//    }

    public StudentDetails() {

    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getStudDetailsId() {
        return studDetailsId;
    }

    public String getStudentDetails() {
        return studentDetails;
    }

    public void setStudDetailsId(final int id) {
        this.studDetailsId = id;
    }

    public void setStudentDetails(String studentDetails) {
        this.studentDetails = studentDetails;
    }

    @Override
    public String toString() {
        return "StudentDetails{" +
                "studDetailsId=" + studDetailsId +
                ", studentDetails='" + studentDetails + '\'' +
                '}';
    }
}
