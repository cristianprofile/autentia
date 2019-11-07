package com.mylab.cromero.service;

import com.mylab.cromero.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CourseServiceImpl implements CourseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private CourseRepository courseRepository;


    @Override
    public List<Course> getAll()
    {
        logger.debug("init getting all courses");
        List<Course> courseList = courseRepository.getAll();
        logger.debug("end getting all courses with list size {}",courseList.size());
        return courseList;
    }

    @Override
    public void addCourse(Course course)
    {
        logger.debug("init creating all courses");
        courseRepository.addCourse(course);
        logger.debug("end creating all courses");
     //TODO ADD COURSE IN DATABASE
    }


}
