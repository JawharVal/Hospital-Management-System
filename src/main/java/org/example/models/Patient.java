package org.example.models;


import lombok.Data;

@Data
public class Patient {
    private String fullName;
    private int age;
    private String gender;
    private Department department;
}