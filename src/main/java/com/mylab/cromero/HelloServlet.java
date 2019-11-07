package com.mylab.cromero;

import com.mylab.cromero.service.Course;
import com.mylab.cromero.service.CourseService;
import com.mylab.cromero.service.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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
            int result = stmt.executeUpdate("CREATE TABLE Course (id INTEGER IDENTITY PRIMARY KEY, title VARCHAR(100) NOT NULL, " +
                    "hours" +
                    " SMALLINT NOT NULL, level VARCHAR(20),active BOOLEAN); ");

            String insert="INSERT INTO course (title, hours,level,active) VALUES ('Learn PHP', 20, 'INTERMEDIATE',TRUE);";
            result = stmt.executeUpdate(insert);

            insert="INSERT INTO course (title, hours,level,active)  VALUES ('Learn JAVA', 10, 'INTERMEDIATE',FALSE);";
            result = stmt.executeUpdate(insert);

        } catch (SQLException e) {
            throw  new ServletException("database error",e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.debug("init get all operation inside servlet");
        List<Course> courses = courseService.getAll();
        req.setAttribute("courses",courses);
        RequestDispatcher rd =
                req.getRequestDispatcher("courses.jsp");
        logger.debug("end get all operation inside servlet");
        rd.forward(req, resp);
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO VALIDATE WITH JSR 303
        logger.debug("init post on inside servlet");
        String title = req.getParameter("title");
        String hours = req.getParameter("hours");
        String level = req.getParameter("level");
        String active = req.getParameter("active");
        Course course = new Course(title,Integer.parseInt(hours), Level.valueOf(level),Boolean.valueOf(active));
        logger.debug("course data received {}",course);
        courseService.addCourse(course);
        logger.debug("end post on inside servlet");
        resp.sendRedirect(req.getContextPath() + "/course");
    }

    
}
