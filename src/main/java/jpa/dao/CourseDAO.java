package jpa.dao;

import jpa.entitymodels.Course;
import jpa.entitymodels.Instructor;

import java.util.List;

public interface CourseDAO {
    List<Course> getAllCourses();
    Instructor getInstructor(String instructorName);
}
