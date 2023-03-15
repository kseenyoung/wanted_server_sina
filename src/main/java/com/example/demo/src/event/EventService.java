package com.example.demo.src.event;



import com.example.demo.config.BaseException;
import com.example.demo.src.event.model.*;
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
public class EventService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EventDao eventDao;
    private final EventProvider eventProvider;
    private final JwtService jwtService;


    @Autowired
    public EventService(EventDao eventDao, EventProvider eventProvider, JwtService jwtService) {
        this.eventDao = eventDao;
        this.eventProvider = eventProvider;
        this.jwtService = jwtService;
    }
    
    public void createEvent(Event req) throws BaseException {
		try{
			Event event = new Event();
			event.setTitle(req.getTitle());
			event.setEventType(req.getEventType()); 
			event.setCharge(req.getCharge());
            System.out.println(">>>>>>>>>>>>>>>>>>"+req.getEventTag());
			event.setEventTag(req.getEventTag());
			event.setEventStatus(req.getEventStatus());
			event.setThumbnail(req.getThumbnail());
			event.setAuthor(req.getAuthor());
			event.setDetail(req.getDetail());
			event.setStartDate(req.getStartDate());
			event.setEndDate(req.getEndDate());

			int result = eventDao.createEvent(event);
			if(result == 0) throw new BaseException(DATABASE_ERROR);
			
		} catch(Exception exception){
			logger.error("App - appendDevices Service Error", exception);
			throw new BaseException(DATABASE_ERROR);
		}
	}
}
