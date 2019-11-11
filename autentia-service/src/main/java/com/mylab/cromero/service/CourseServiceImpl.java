package com.mylab.cromero.service;

import com.mylab.cromero.repository.CourseRepository;
import com.mylab.cromero.service.domain.Course;
import com.mylab.cromero.service.exception.DataAccessException;
import com.mylab.cromero.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.List;

@Singleton
public class CourseServiceImpl implements CourseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private CourseRepository courseRepository;


    @Override
    public List<Course> getAll() throws ServiceException
    {
        logger.debug("Init getting all courses");
        List<Course> courseList = null;
        try {
            courseList = courseRepository.getAll();
        } catch (SQLException e) {
            logger.error("Data access exception ",e);
            throw new DataAccessException("Data access exception ");
        }
        logger.debug("End getting all courses with list size {}",courseList.size());
        return courseList;
    }

    @Override
    public void addCourse(Course course) throws ServiceException
    {
        logger.debug("Init creating  courses");
        try {
            courseRepository.addCourse(course);
        } catch (SQLException e) {
            logger.error("Data access exception ",e);
            throw new DataAccessException("Data access exception ");
        }
        logger.debug("End creating courses");
    }


}
