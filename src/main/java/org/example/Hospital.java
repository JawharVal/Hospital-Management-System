package org.example;
import lombok.Data;


import org.example.Patient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Data
@ApplicationScoped //аннотация области bean-компонента
//означает, что в приложении будет только один экземпляр класса Hospital,
// и этот экземпляр будет использоваться всеми запросами.
public class Hospital {

    @Inject
    //используется для внедрения зависимостей, что позволяет автоматически создавать экземпляры объектов и
    // внедрять их в другие объекты, а не создавать их вручную и управлять ими.
    private List<Department> departments;

    public Hospital() {
        departments = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public boolean removeDepartment(String departmentName) {
        for (Department department : departments) {
            if (department.getName().equals(departmentName)) {
                departments.remove(department);
                return true;
            }
        }
        return false;
    }
    public boolean departmentExists(String departmentName) {
        return getDepartmentByName(departmentName) != null;
    }
    public void getDepartmentInformation() {
        System.out.println("Department Information:");
        for (Department department : departments) {
            System.out.println("Department Name: " + department.getName());
            System.out.println("Number of Patients: " + department.getNumberOfPatients());
            System.out.println("--------------------");
        }
    }

    public Department getDepartmentByName(String departmentName) {
        for (Department department : departments) {
            if (department.getName().equals(departmentName)) {
                return department;
            }
        }
        return null;
    }

    public void getPatientInformation() {
        System.out.println("Patient Information:");
        for (Department department : departments) {
            department.displayPatients();
        }
    }
}
