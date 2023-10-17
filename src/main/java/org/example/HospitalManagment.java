package org.example;

import org.example.Hospital;

import javax.inject.Inject;
import java.util.InputMismatchException;
import java.util.Scanner;

class HospitalManagementApp {
    //Благодаря аннотации @Inject классу HospitalManagementApp не нужно вручную создавать экземпляр класса Hospital.
    //Вместо этого платформа внедрения зависимостей заботится о создании экземпляра и внедрении объекта Hospital в
    //класс HospitalManagementApp.
    @Inject
    private Hospital hospital;
    private final Scanner scanner;

    public HospitalManagementApp() {
        scanner = new Scanner(System.in);
    }

    // Injected constructor:
    // Dependency injection предоставит экземпляр класса Hospital и назначит его полю Hospital объекта HospitalManagementApp.

    @Inject
    public HospitalManagementApp(Hospital hospital) {
        this.hospital = hospital;
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addDepartment();
                    break;
                case 2:
                    removeDepartment();
                    break;
                case 3:
                    addPatientToDepartment();
                    break;
                case 4:
                    removePatientFromDepartment();
                    break;
                case 5:
                    editDepartment();
                    break;
                case 6:
                    editPatient();
                    break;
                case 7:
                    hospital.getDepartmentInformation();
                    break;
                case 8:
                    hospital.getPatientInformation();
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add Department");
        System.out.println("2. Remove Department");
        System.out.println("3. Add Patient to Department");
        System.out.println("4. Remove Patient from Department");
        System.out.println("5. Edit Department");
        System.out.println("6. Edit Patient");
        System.out.println("7. Display Department Information");
        System.out.println("8. Display Patient Information");
        System.out.println("9. Exit");
    }

    private int getUserChoice() {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice (1-9).");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume newline character
        return choice;
    }

    private void addDepartment() {
        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();

        if (hospital.departmentExists(departmentName)) {
            System.out.println("Department with the same name already exists. Please choose a unique name.");
            return;
        }

        Department department = new Department(departmentName);
        hospital.addDepartment(department);
        System.out.println("Department added successfully.");
    }

    private void removeDepartment() {
        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();
        boolean removed = hospital.removeDepartment(departmentName);
        if (removed) {
            System.out.println("Department removed successfully.");
        } else {
            System.out.println("Department not found.");
        }
    }
    public static boolean isValidName(String name) {
        return name.matches("^[A-Za-z ]+$");
    }
    private boolean isValidAge(int age) {
        return age >= 0 && age <= 150; // Adjust the age range as needed
    }

    private boolean isValidGender(String gender) {
        return gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female");
    }

    private void addPatientToDepartment() {
        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();
        Department department = hospital.getDepartmentByName(departmentName);
        if (department != null) {
            String patientName = "";
            boolean validNameInput = false;
            while (!validNameInput) {
                System.out.print("Enter patient name: ");
                patientName = scanner.nextLine();
                if (isValidName(patientName)) {
                    validNameInput = true;
                } else {
                    System.out.println("Invalid name. Please enter a valid name.");
                }
            }

            int patientAge = -1;
            boolean validAgeInput = false;
            while (!validAgeInput) {
                try {
                    System.out.print("Enter patient age: ");
                    patientAge = scanner.nextInt();
                    if (isValidAge(patientAge)) {
                        validAgeInput = true;
                    } else {
                        System.out.println("Invalid age. Please enter a valid age.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid age.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
            scanner.nextLine(); // Consume newline character

            String patientGender = "";
            boolean validGenderInput = false;
            while (!validGenderInput) {
                System.out.print("Enter patient gender: ");
                patientGender = scanner.nextLine();
                if (isValidGender(patientGender)) {
                    validGenderInput = true;
                } else {
                    System.out.println("Invalid gender. Please enter 'male' or 'female'.");
                }
            }

            Patient patient = new Patient(patientName, patientAge, patientGender);
            department.addPatient(patient);
            System.out.println("Patient added to department successfully.");
        } else {
            System.out.println("Department not found.");
        }
    }


    private void removePatientFromDepartment() {
        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();
        Department department = hospital.getDepartmentByName(departmentName);
        if (department != null) {
            System.out.print("Enter patient name: ");
            String patientName = scanner.nextLine();
            boolean removed = department.removePatient(patientName);
            if (removed) {
                System.out.println("Patient removed from department successfully.");
            } else {
                System.out.println("Patient not found in the department.");
            }
        } else {
            System.out.println("Department not found.");
        }
    }

    private void editDepartment() {
        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();
        Department department = hospital.getDepartmentByName(departmentName);
        if (department != null) {
            System.out.print("Enter new department name: ");
            String newDepartmentName = scanner.nextLine();

            if (hospital.departmentExists(newDepartmentName)) {
                System.out.println("Department with the new name already exists. Please choose a unique name.");
                return;
            }

            department.setName(newDepartmentName);
            System.out.println("Department name updated successfully.");
        } else {
            System.out.println("Department not found.");
        }
    }


    private void editPatient() {
        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();
        Department department = hospital.getDepartmentByName(departmentName);
        if (department != null) {
            System.out.print("Enter patient name: ");
            String patientName = scanner.nextLine();
            Patient patient = department.getPatientByName(patientName);
            if (patient != null) {
                String newPatientName = "";

                // Add the name validation loop here
                boolean validNameInput = false;
                while (!validNameInput) {
                    System.out.print("Enter new patient name: ");
                    newPatientName = scanner.nextLine();
                    if (isValidName(newPatientName) && !newPatientName.isEmpty()) {
                        validNameInput = true;
                    } else {
                        System.out.println("Invalid name. Please enter a valid non-empty name.");
                    }
                }

                int newPatientAge = -1;
                boolean validAgeInput = false;
                while (!validAgeInput) {
                    try {
                        System.out.print("Enter new patient age: ");
                        newPatientAge = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        if (isValidAge(newPatientAge)) {
                            validAgeInput = true;
                        } else {
                            System.out.println("Invalid age. Please enter a valid age.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid age.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                }

                String newPatientGender = "";
                boolean validGenderInput = false;
                while (!validGenderInput) {
                    System.out.print("Enter new patient gender: ");
                    newPatientGender = scanner.nextLine();
                    if (isValidGender(newPatientGender)) {
                        validGenderInput = true;
                    } else {
                        System.out.println("Invalid gender. Please enter 'male' or 'female'.");
                    }
                }

                patient.setFullName(newPatientName);
                patient.setAge(newPatientAge);
                patient.setGender(newPatientGender);
                System.out.println("Patient information updated successfully.");
            } else {
                System.out.println("Patient not found.");
            }
        } else {
            System.out.println("Department not found.");
        }
    }

}
