package org.example.Services;

import org.example.DI.Component;
import org.example.models.Department;
import org.example.repositories.DepartmentRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentService implements DepartmentRepository {
    private final List<Department> departments = new ArrayList<>();

    public Department createDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        departments.add(department);
        return department;
    }

    public void deleteDepartment(Department department) {
        departments.remove(department);
    }

    public void updateDepartment(Department department, String newName) {
        if (department != null) {
            department.setName(newName);
        } else {
            System.out.println("Department not found.");
        }
    }


//запускает поток по коллекции отделов. get department name
    public Department getDepartmentByName(String name) {
        return departments.stream()
                //включаем только те объекты отдела, имя которых соответствует указанному параметру lambda placeholder
                .filter(d -> d.getName().equals(name))
                //прекращает обработку, как только находит совпадение
                .findFirst()
                .orElse(null);
    }

    // used to display Departments Information
    public List<Department> getAllDepartments() {
        return departments;
    }

}