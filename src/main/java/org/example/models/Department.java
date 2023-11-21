package org.example.models;

import lombok.Data;

@Data
public class Department {
    private String name;
    private int numberOfPatients; // по умолчанию = 0

    public void incrementPatients() {
        numberOfPatients++;
    }

    public void decrementPatients() {
        numberOfPatients--;
    }
}

