package com.example.demo.src.company.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private int id;
    private String name;
    private String explanation;
    private int totalEmployees;
    private String status;
}
