package com.mylab.cromero.repository;

import com.mylab.cromero.service.domain.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherRepository {
    List<Teacher> getAll() throws SQLException;
}
