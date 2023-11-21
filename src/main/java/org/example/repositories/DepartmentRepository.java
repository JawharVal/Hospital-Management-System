package org.example.repositories;

import org.example.models.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentRepository {
    Department createDepartment(String name) throws SQLException;
    List<Department> getAllDepartments() throws SQLException;
    Department getDepartmentByName(String name) throws SQLException;
    void updateDepartment(Department department, String newName) throws SQLException;
    void deleteDepartment(Department department) throws SQLException;
}