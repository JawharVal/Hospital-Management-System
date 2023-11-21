# Hospital Management System

This project is a Java application for managing departments and patients within them using CRUD (Create, Read, Update, Delete) operations. It provides a simple console-based user interface for interacting with the system.

## Features

- Create, read, update, and delete operations for both departments and patients.
- Dependency injection using a `BeanFactory` class with `@Inject` and `@Component` annotations.
- Utilizes Lombok for reducing boilerplate code.
- Interactive console interface for user input to perform CRUD operations.

## Prerequisites

- Java Development Kit (JDK) installed 
- Apache Maven for building the project 

## Getting Started

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/your-username/department-patient-management.git
Navigate to the project directory:

bash
Copy code
cd department-patient-management
Build the project (if not using an IDE with built-in Maven support):

bash
Copy code
mvn clean install
Run the application:

bash
Copy code
java -jar target/department-patient-management.jar
Usage
Upon running the application, the user will be presented with a menu to choose CRUD operations for departments and patients.

Follow the on-screen prompts to perform the desired CRUD operations.

The application will display appropriate messages and updates based on the user's choices.

Dependencies
Lombok: Used for reducing boilerplate code.
