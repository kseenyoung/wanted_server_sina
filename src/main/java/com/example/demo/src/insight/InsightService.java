package com.example.demo.src.insight;



import com.example.demo.config.BaseException;
import com.example.demo.src.insight.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

// Service Create, Update, Delete 의 로직 처리
@Service
public class InsightService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final InsightDao insightDao;
    private final InsightProvider insightProvider;
    private final JwtService jwtService;


    @Autowired
    public InsightService(InsightDao insightDao, InsightProvider insightProvider, JwtService jwtService) {
        this.insightDao = insightDao;
        this.insightProvider = insightProvider;
        this.jwtService = jwtService;
    }
    
}
