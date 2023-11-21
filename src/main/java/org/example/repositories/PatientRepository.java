package org.example.repositories;

import org.example.models.Department;
import org.example.models.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientRepository {
    Patient createPatient(String fullName, int age, String gender, String departmentName) throws SQLException;
    List<Patient> findPatientsByName(String fullName) throws SQLException;
    void updatePatient(Patient patient, String newFullName, int newAge, String newGender) throws SQLException;
    void deletePatient(Patient patient) throws SQLException;
    List<Patient> getAllPatients() throws SQLException;
    Patient findPatientByName(String fullName) throws SQLException;
    List<Patient> getPatientsByDepartment(Department department) throws SQLException;
}