package jpa.entitymodels;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="instructor")
public class Instructor {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_gen")
    @SequenceGenerator(name="instructor_gen", sequenceName = "instructor_seq") //, initialValue = 10, allocationSize = 100
    @Id
    @Column(name = "id")
    private int iId;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> listCourses = new ArrayList<>();

//    public Instructor(String name) {
//        this.name = name;
//    }

    public Instructor() {
    }

    public void addCourse(Course course) {
//        if (listCourses == null) {
//            listCourses = new ArrayList<>();
//        }
        listCourses.add(course);
        course.setInstructor(this);
    }

    public int getiId() {
        return iId;
    }
    public void setiId(int id) {
        this.iId = id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getListCourses() {
        return listCourses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListCourses(List<Course> listCourses) {
        this.listCourses = listCourses;
    }

    @Override
    public String toString() {
        return "Instructor: code "+
                this.iId+", "+
                this.name;
    }
}
