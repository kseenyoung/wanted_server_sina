package com.example.demo.src.event.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEventFilterReq {
    private String sort;
    private String eventTypeCd;
    private String eventTypeNm;
    private String charge;
    private String[] EventTag;
}

