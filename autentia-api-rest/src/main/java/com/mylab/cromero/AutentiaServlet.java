package com.mylab.cromero;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylab.cromero.service.Course;
import com.mylab.cromero.service.CourseService;
import com.mylab.cromero.service.Teacher;
import com.mylab.cromero.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

//TODO MAP CORRECT HTTP RESPONSE CODE
@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/course"}
    )
public class AutentiaServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private CourseService courseService;

    @Inject
    private TeacherService teacherService;


    @Resource(name = "jdbc/UsersDB")
    private DataSource dataSource;

    private final ObjectMapper mapper = new ObjectMapper();


    //TODO refactor database init code. Do not access to data layer inside a Servlet

    /**
     * Initial servlet create database in memory with 2 courses (course table)
     * @param config
     * @throws ServletException
     */
    public void init(ServletConfig config) throws ServletException {

        try {
            //TODO refactor database init code. Do not access to data layer inside a Servlet. See Spring example code
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            int result = stmt.executeUpdate("CREATE TABLE Teacher (id INTEGER IDENTITY PRIMARY KEY, name VARCHAR(100)" +
                    " " + "NOT NULL, " + " email VARCHAR(20));");
            result = stmt.executeUpdate("CREATE TABLE Course (id INTEGER IDENTITY PRIMARY KEY, title VARCHAR(100) NOT NULL, " +
                    "hours" +
                    " SMALLINT NOT NULL, level VARCHAR(20),active BOOLEAN,teacherId INTEGER ,FOREIGN KEY (teacherId) " +
                    "REFERENCES Teacher(id)); ");


            String insert="INSERT INTO teacher (id,name, email) VALUES (1,'Jorge Romero', 'jjj@ole.com');";
            result = stmt.executeUpdate(insert);

            insert="INSERT INTO teacher (id,name, email) VALUES (2,'Adolfo Dominguez', 'ddd@ole.com');";
            result = stmt.executeUpdate(insert);

            insert="INSERT INTO Course (title, hours,level,active,teacherId) VALUES ('Learn PHP', 20, 'INTERMEDIATE'," +
                    "TRUE,1);";
            result = stmt.executeUpdate(insert);

            insert="INSERT INTO Course (title, hours,level,active,teacherId)  VALUES ('Learn JAVA', 10, " +
                    "'INTERMEDIATE',FALSE,2);";
            result = stmt.executeUpdate(insert);


        } catch (SQLException e) {
            throw  new ServletException("database error",e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.debug("init get all operation inside servlet");

        //TODO PREPARE PAGINATION
        // int currentPage = Integer.valueOf(req.getParameter("currentPage"));
        // int recordsPerPage = Integer.valueOf(req.getParameter("recordsPerPage"));

        List<Course> courseList = courseService.getAll();
        req.setAttribute("courses",courseList);

        List<Teacher> teacherList = teacherService.getAll();
        req.setAttribute("teachers",teacherList);

        resp.setContentType("application/json");
        // Get the printwriter object from response to write the required json object to the output stream
        PrintWriter out = resp.getWriter();

        String json = mapper.writeValueAsString(courseList);

        out.print(json);
        logger.debug("end  get all operation inside servlet {}",json);
        out.flush();
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO VALIDATE WITH JSR 303

        try (BufferedReader reader = req.getReader()) {
            String json = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            Course course = mapper.readValue(json, Course.class);
            logger.debug("init post on inside servlet");
            logger.debug("course data received {}", course);
            courseService.addCourse(course);
            logger.debug("end post on inside servlet");

        } catch (Exception e) {
            e.printStackTrace();
            /*report an error*/
        }


    }


    
}
