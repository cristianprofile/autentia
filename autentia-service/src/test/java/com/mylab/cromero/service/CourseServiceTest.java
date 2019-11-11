package com.mylab.cromero.service;

import com.mylab.cromero.repository.CourseRepository;
import com.mylab.cromero.service.domain.Course;
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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;

    @Test
    public void testGetAllEmptyList() throws SQLException {

        when(this.courseRepository.getAll()).thenReturn(Collections.emptyList());
        List<Course> all = courseService.getAll();
        Assert.assertEquals(all.size(),0);
    }

    @Test
    public void testGetAllNonEmptyList() throws SQLException {

        EasyRandom easyRandom = new EasyRandom();
        Course course1 = easyRandom.nextObject(Course.class);
        Course course2 = easyRandom.nextObject(Course.class);

        List<Course> courses = Arrays.asList(course1, course2);
        when(this.courseRepository.getAll()).thenReturn(courses);

        List<Course> all = courseService.getAll();

        Assert.assertEquals(all.size(),2);
        Assert.assertEquals(all.get(0),course1);
        Assert.assertEquals(all.get(1),course2);
    }

    @Test(expected = ServiceException.class)
    public void testGetAllWithSqlException() throws SQLException {

        when(this.courseRepository.getAll()).thenThrow(SQLException.class);
        List<Course> all = courseService.getAll();

    }


    @Test
    public void testAddCourseOk() throws SQLException {

        EasyRandom easyRandom = new EasyRandom();
        Course course1 = easyRandom.nextObject(Course.class);
        doNothing().when(courseRepository).addCourse(course1);
        courseService.addCourse(course1);
        verify(this.courseRepository, times(1)).addCourse(course1);

    }

    @Test(expected = ServiceException.class)
    public void testAddCourseWithSqlException() throws SQLException {

        EasyRandom easyRandom = new EasyRandom();
        Course course1 = easyRandom.nextObject(Course.class);
        doThrow(SQLException.class)
                .when(courseRepository).addCourse(course1);
        courseService.addCourse(course1);
        verify(this.courseRepository, times(1)).addCourse(course1);

    }



}
