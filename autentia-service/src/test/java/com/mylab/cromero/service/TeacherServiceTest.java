package com.mylab.cromero.service;

import com.mylab.cromero.repository.TeacherRepository;
import com.mylab.cromero.service.domain.Teacher;
import com.mylab.cromero.service.exception.ServiceException;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceTest {


    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Mock
    private TeacherRepository teacherRepository;

    @Test
    public void testGetAllEmptyList() throws SQLException {

        when(this.teacherRepository.getAll()).thenReturn(Collections.emptyList());

        List<Teacher> all = teacherService.getAll();

        Assert.assertEquals(all.size(),0);
    }


    @Test
    public void testGetAllNonEmptyList() throws SQLException {

        EasyRandom easyRandom = new EasyRandom();
        Teacher teacher1 = easyRandom.nextObject(Teacher.class);
        Teacher teacher2 = easyRandom.nextObject(Teacher.class);

        List<Teacher> teacherList = Arrays.asList(teacher1, teacher2);
        when(this.teacherRepository.getAll()).thenReturn(teacherList);

        List<Teacher> all = teacherService.getAll();

        Assert.assertEquals(all.size(),2);
        Assert.assertEquals(all.get(0),teacher1);
        Assert.assertEquals(all.get(1),teacher2);
    }

    @Test(expected = ServiceException.class)
    public void testGetAllWithSqlException() throws SQLException {

        when(this.teacherRepository.getAll()).thenThrow(SQLException.class);
        teacherService.getAll();
    }

}
