//import jdk.nashorn.internal.objects.annotations.Getter;
//import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;

@Entity
@Table(name = "studentdetails")
public class StudentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="studDetailsId")
    private int studDetailsId;
    @Column(name="studentDetails")
    private String studentDetails;

    public StudentDetails(String studentDetails) {
        //this.studDetailsId = studDetailsId;
        this.studentDetails = studentDetails;
    }

    public StudentDetails() {

    }

    public int getStudDetailsId() {
        return studDetailsId;
    }

    public String getStudentDetails() {
        return studentDetails;
    }

    public void setStudDetailsId(int studDetailsId) {
        this.studDetailsId = studDetailsId;
    }

    public void setStudentDetails(String studentDetails) {
        this.studentDetails = studentDetails;
    }
}
