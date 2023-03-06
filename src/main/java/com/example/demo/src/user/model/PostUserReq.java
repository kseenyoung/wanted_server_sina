package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserReq {
    private String name;
    private String email;
    private String password;
    private String phoneNational;
    private String phoneNumber;
    private int marketingAgreement;
}
