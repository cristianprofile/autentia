package com.mylab.cromero;

import com.mylab.cromero.service.Course;
import com.mylab.cromero.service.CourseService;
import com.mylab.cromero.service.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/course"}
    )
public class HelloServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private CourseService courseService;


    @Resource(name = "jdbc/UsersDB")
    private DataSource dataSource;

    /**
     * Initial servlet create database in memory with 2 courses (course table)
     * @param config
     * @throws ServletException
     */
    public void init(ServletConfig config) throws ServletException {

        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            int result = stmt.executeUpdate("CREATE TABLE course (id INT NOT NULL, title VARCHAR(100) NOT NULL, " +
                    "hours" +
                    " SMALLINT NOT NULL, level VARCHAR(20),active BOOLEAN, PRIMARY KEY (id)); ");

            String insert="INSERT INTO course VALUES (1,'Learn PHP', 20, 'INTERMEDIATE',TRUE);";
            result = stmt.executeUpdate(insert);

            insert="INSERT INTO course VALUES (2,'Learn JAVA', 10, 'INTERMEDIATE',FALSE);";
            result = stmt.executeUpdate(insert);

        } catch (SQLException e) {
            throw  new ServletException("database error",e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.debug("init get all operation inside servlet");
        ServletOutputStream out = resp.getOutputStream();
        out.write("hello student servlet".getBytes());
        List<Course> all = courseService.getAll();
        all.forEach(course1 -> {
            try {
                out.write(course1.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        out.flush();
        out.close();
        logger.debug("end get all operation inside servlet");
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        logger.debug("init post on inside servlet");
        Course course = new Course("title55",21, Level.ADVANCE,true);
        courseService.addCourse(course);
        logger.debug("end post on inside servlet");

    }

    
}
