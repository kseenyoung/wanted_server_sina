package com.example.demo.src.employment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employment {
    private int id;
    private String name;
    private String category;
    private String subCategory;
    private String country;
    private String city;
    private int compensation;
    private String imageUrl;
}
