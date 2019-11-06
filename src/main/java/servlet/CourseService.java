package servlet;

import java.util.List;

public interface CourseService {
    List<Course> getAll();

    void addCourse(Course course);
}
