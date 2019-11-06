package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/course"}
    )
public class HelloServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private CourseService courseService;

    //TODO Add correlation id in mdc
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
        Course course = new Course("title55",21,Level.ADVANCE,true);
        courseService.addCourse(course);
        logger.debug("end post on inside servlet");

    }

    
}
