package com.mylab.cromero.repository;

import com.mylab.cromero.service.domain.Teacher;
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

@Singleton
public class TeacherRepositoryImpl  implements TeacherRepository{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "jdbc/UsersDB")
    private DataSource dataSource;

    @Override
    public List<Teacher> getAll() throws SQLException {

        logger.debug("init getting all teachers");
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement()){
            String sql = "select * from Teacher";
            ResultSet rs = stmt.executeQuery(sql);
            List<Teacher> courseList = createList(rs);
            logger.debug("end getting all teachers size {}",courseList.size());
            return courseList;

        }

    }
    @Override
    //TODO REFACTOR DUPLICATE REPOSITORY METHOD
    public List<Teacher> createList(ResultSet rs) throws SQLException {
        ArrayList<Teacher> courses = new ArrayList<Teacher>();
        while (rs.next()) {
            Teacher teacher = createTeacher(rs);
            courses.add(teacher);
        }
        return courses;

    }

    private Teacher createTeacher(ResultSet rs) throws SQLException {
        return new Teacher(rs.getInt("id"),rs.getString("name"), rs.getString("email"));
    }
}
