package org.example;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main {
    public static void main(String[] args) {
        // creates an instance of the Weld class and initializes it.
        WeldContainer container = new Weld().initialize();
        // select an instance of the Hospital class and inject it into the hospital variable.
        // The select() method is used to obtain a reference to the Hospital bean managed by Weld,
        // and the get() method retrieves the actual instance.
        Hospital hospital = container.select(Hospital.class).get();
        //the Hospital instance obtained from the Weld container is passed as a parameter to the constructor of the HospitalManagementApp class,
        // allowing dependency injection to take place.
        HospitalManagementApp app = new HospitalManagementApp(hospital);
        //starts the hospital management application.
        app.start();
        // shut down the container.
        container.shutdown();
    }
}