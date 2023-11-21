package org.example;

import org.example.DI.BeanFactory;

public class main {
    public static void main(String[] args) {
        // Initialize the DI container
        BeanFactory.init("org.example");
        // Create an instance of HospitalApp
        HospitalApp hospitalApp = BeanFactory.getBean(HospitalApp.class);
        // Start the application
        hospitalApp.start();

    }
}
