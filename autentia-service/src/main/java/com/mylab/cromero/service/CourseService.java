package com.mylab.cromero.service;

import com.mylab.cromero.service.domain.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAll();

    void addCourse(Course course);
}
