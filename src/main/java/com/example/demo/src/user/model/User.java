package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int ID;
    private String name;
    private String password;
    private String email;
    private String phoneNational;
    private String phoneNumber;
    private String imgUrl;
    private int marketingAgreement;
}
