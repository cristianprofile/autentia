package com.mylab.cromero.repository;

import com.mylab.cromero.service.Course;
import com.mylab.cromero.service.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class CourseRepositoryImpl implements CourseRepository {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Course> getAll(){
        logger.debug("init getting all courses");
        List<Course> courseList = new ArrayList<>();
        Course course = new Course("title1",22, Level.ADVANCE,true);
        Course course2 = new Course("title4",25,Level.INTERMEDIATE,false);
        courseList.add(course);
        courseList.add(course2);
        logger.debug("end getting all courses");
        return courseList;
    }


    @Override
    public void addCourse(Course course)
    {

        //TODO ADD COURSE IN DATABASE
    }

}
