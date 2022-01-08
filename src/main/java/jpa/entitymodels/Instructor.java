package jpa.entitymodels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int iId;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> listCourses;

    public Instructor(String name) {
        this.name = name;
        this.listCourses = new ArrayList<>();
    }
    public Instructor() {
    }
    public void addCourse(Course course) {
        if (listCourses == null) {
            listCourses = new ArrayList<>();
        }
        listCourses.add(course);
        course.setInstructor(this);
    }

    public int getiId() {
        return iId;
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
