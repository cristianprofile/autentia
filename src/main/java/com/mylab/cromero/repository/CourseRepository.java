package com.mylab.cromero.repository;

import com.mylab.cromero.service.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> getAll();

    void addCourse(Course course);
}