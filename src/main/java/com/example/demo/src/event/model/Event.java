package com.example.demo.src.event.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private int id;
    private String title;
    private String eventType;
    private String charge;
    private String eventTag;
    private String startDate;
    private String endDate;
    private String author;
    private String eventStatus;
    private String thumbnail;
    private String detail;
    private String location;
    private String status;
}

