package com.mylab.cromero.service;

import com.mylab.cromero.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAll()
    {
        logger.debug("init getting all teachers");
        List<Teacher> courseList = teacherRepository.getAll();
        logger.debug("end getting all teachers with list size {}",courseList.size());
        return courseList;
    }
}
