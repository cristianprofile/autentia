package com.mylab.cromero.service;

import com.mylab.cromero.repository.TeacherRepository;
import com.mylab.cromero.service.domain.Teacher;
import com.mylab.cromero.service.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAll()
    {
        logger.debug("Init getting all teachers");
        List<Teacher> courseList = null;
        try {
            courseList = teacherRepository.getAll();
        } catch (SQLException e) {
            logger.error("Data access exception ",e);
            throw new DataAccessException("Data access exception ");
        }
        logger.debug("End getting all teachers with list size {}",courseList.size());
        return courseList;
    }
}
