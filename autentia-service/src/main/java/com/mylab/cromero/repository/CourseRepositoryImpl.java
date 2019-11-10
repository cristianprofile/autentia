package com.mylab.cromero.repository;

import com.mylab.cromero.service.Course;
import com.mylab.cromero.service.Level;
import com.mylab.cromero.service.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Singleton
public class CourseRepositoryImpl implements CourseRepository {

    //  !!!!! TODO EXCEPTION MAPPING WITH CUSTOM HIERARCHY

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "jdbc/UsersDB")
    private DataSource dataSource;

    @Override
    public List<Course> getAll() throws RuntimeException {
        logger.debug("init getting all courses");
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement()){
            String sql = "select C.title,C.level,C.hours,C.active,T.id as teacherId,T.name teacherName,T.email as " +
                    "teacherEmail " +
                    "from " +
                    "Course as C " +
                    "inner JOIN Teacher as T ON C.teacherId = T.id " +
                    "where C.active" +
                    " order by C.title " +
                    "asc";
            ResultSet rs = stmt.executeQuery(sql);
            List<Course> courseList = createList(rs);
            logger.debug("end getting all courses size {}",courseList.size());
            return courseList;

        } catch (SQLException e) {
            //TODO CHANGE WITH CUSTOM EXCEPTION
            throw  new RuntimeException("exception in database",e);
        }

    }


    @Override
    public void addCourse(Course course)
    {

        //TODO INSERT SENTENCE
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement()){
            logger.debug("init creating a courses");

            String sql = "INSERT INTO Course (title, hours,level,active,teacherId) " + "VALUES ('" +course.getTitle()+
                    "',"+course.getHours()+",'"+course.getLevel().name()+"', "+course.getActive()+","+course.getTeacher().getId()+")";
            stmt.execute(sql);
            logger.debug("end creating a course");
            // end transaction block, commit changes
            connection.commit();
            // good practice to set it back to default true
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            //TODO CHANGE WITH CUSTOM EXCEPTION
            throw  new RuntimeException("exception in database",e);
        }


        //TODO ADD COURSE IN DATABASE
    }


    private ArrayList<Course> createList(ResultSet rs) throws SQLException

    {
        ArrayList<Course> courses = new ArrayList<>();
        while (rs.next()) {
            Course course = createCourse(rs);
            courses.add(course);
        }
        return courses;
    }

    private Stream<Course> createStream(ResultSet rs) throws SQLException

    {
        Stream.Builder<Course> builder = Stream.builder();
        while (rs.next()) {
            Course course = createCourse(rs);
            builder.add(course);
        }
        return builder.build();
    }
    //TODO catch exception custom exception hierarchy

    private static Course createCourse(ResultSet rs) throws SQLException {
        return new Course(rs.getString("title"), rs.getInt("hours"), Level.valueOf(rs.getString("level")),
                rs.getBoolean("active"),
                new Teacher(rs.getInt("teacherId"),rs.getString("teacherName"),rs.getString("teacherEmail"))
        );
    }

}
