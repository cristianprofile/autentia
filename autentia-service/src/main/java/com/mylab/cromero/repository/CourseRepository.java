package com.mylab.cromero.repository;

import com.mylab.cromero.service.domain.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseRepository {

    List<Course> getAll() throws SQLException;

    void addCourse(Course course) throws SQLException;
}
