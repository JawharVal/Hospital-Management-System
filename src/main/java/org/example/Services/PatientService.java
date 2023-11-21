package org.example.Services;

import org.example.DI.Component;
import org.example.DI.Inject;
import org.example.models.Department;
import org.example.models.Patient;
import org.example.repositories.PatientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientService implements PatientRepository {
    @Inject
    private DepartmentService departmentServices;
    private final List<Patient> patients = new ArrayList<>();

    public Patient createPatient(String fullName, int age, String gender, String departmentName) {
        if (departmentServices != null) {
            Department department = departmentServices.getDepartmentByName(departmentName);
            if (department == null) {
                department = departmentServices.createDepartment(departmentName);
            }

            Patient patient = new Patient();
            patient.setFullName(fullName);
            patient.setAge(age);
            patient.setGender(gender);
            patient.setDepartment(department);
            patients.add(patient);
            department.incrementPatients();
            return patient;
        } else {
            // Handle the case when DepartmentService is not injected
            System.err.println("DepartmentService is not injected. Unable to create patient.");
            return null;
        }
    }


    public void deletePatient(Patient patient) {
        if (patients.remove(patient)) {
            patient.getDepartment().decrementPatients();
        }
    }

    public void updatePatient(Patient patient, String newFullName, int newAge, String newGender) {
        if (patients.contains(patient)) {
            patient.setFullName(newFullName);
            patient.setAge(newAge);
            patient.setGender(newGender);
        }
    }

    // used to display Patients by Department
    public List<Patient> getPatientsByDepartment(Department department) {
        return patients.stream()
                .filter(p -> p.getDepartment() == department) // check department of patient list and input and add to list if exist
                .toList();
    }

    public List<Patient> getAllPatients() {
        return patients;
    }

    // used for edit patient
    public Patient findPatientByName(String fullName) {
        return patients.stream()
                .filter(p -> p.getFullName().equals(fullName))
                .findFirst()
                .orElse(null);
    }

    // used to delete patients
    public List<Patient> findPatientsByName(String fullName) {
        return patients.stream()
                .filter(p -> p.getFullName().equals(fullName))
                .collect(Collectors.toList()); // return a list with found names
    }

}