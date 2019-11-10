package com.mylab.cromero.service;

import java.util.List;

public interface CourseService {
    List<Course> getAll();

    void addCourse(Course course);
}
