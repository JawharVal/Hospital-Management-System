package org.example;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class Department {
    private String name;
    private List<Patient> patients;

    public Department(String name) {
        this.name = name;
        this.patients = new ArrayList<>();
    }
    // GENERATED AUTOMATICALLY BY @DATA LOMBOK , it generates standard getters and setters.( not custom ofc)
    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    */
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public boolean removePatient(String patientName) {
        for (Patient patient : patients) {
            if (patient.getFullName().equals(patientName)) {
                patients.remove(patient);
                return true;
            }
        }
        return false;
    }

    public int getNumberOfPatients() {
        return patients.size();
    }

    public Patient getPatientByName(String patientName) {
        for (Patient patient : patients) {
            if (patient.getFullName().equals(patientName)) {
                return patient;
            }
        }
        return null;
    }

    public void displayPatients() {
        System.out.println("Patients in Department " + name + ":");
        for (Patient patient : patients) {
            System.out.println("Name: " + patient.getFullName());
            System.out.println("Age: " + patient.getAge());
            System.out.println("Gender: " + patient.getGender());
            System.out.println("--------------------");
        }
    }
}