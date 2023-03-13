package com.example.demo.src.insight.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Insight {
    private int id;
    private String title;
    private String contentType;
    private String contentUrl;
    private String author;
    private String explanation;
    private String thumbnail;
}
