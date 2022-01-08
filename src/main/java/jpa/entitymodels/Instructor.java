package jpa.entitymodels;

import javax.persistence.*;
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

    public Instructor(String name, List<Course> listCourses) {
        this.name = name;
        this.listCourses = listCourses;
    }

    public Instructor() {

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
}
