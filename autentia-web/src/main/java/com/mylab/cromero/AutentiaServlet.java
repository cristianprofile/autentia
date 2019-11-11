package com.mylab.cromero;

import com.mylab.cromero.service.CourseService;
import com.mylab.cromero.service.TeacherService;
import com.mylab.cromero.service.domain.Course;
import com.mylab.cromero.service.domain.Level;
import com.mylab.cromero.service.domain.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

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

        RequestDispatcher rd =
                req.getRequestDispatcher("courses.jsp");
        logger.debug("end get all operation inside servlet");

        rd.forward(req, resp);
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO VALIDATE WITH JSR 303
        // TODO EXTRACT LITERALS TO CONSTANT OR ENUM CLASS
        logger.debug("init post on inside servlet");
        String title = req.getParameter("title");
        String hours = req.getParameter("hours");
        String level = req.getParameter("level");
        String active = req.getParameter("active");
        String teacherId = req.getParameter("teacherId");
        Course course = new Course(title,Integer.parseInt(hours), Level.valueOf(level),Boolean.valueOf(active),
                new Teacher(Integer.valueOf(teacherId)));
        logger.debug("course data received {}",course);
        courseService.addCourse(course);
        logger.debug("end post on inside servlet");
        resp.sendRedirect(req.getContextPath() + "/course");
    }


    
}
