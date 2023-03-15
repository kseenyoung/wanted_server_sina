package com.example.demo.src.event;


import com.example.demo.config.BaseException;
import com.example.demo.src.event.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class EventProvider {

    private final EventDao eventDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    // @Autowired
    public EventProvider(EventDao eventDao, JwtService jwtService) {
        this.eventDao = eventDao;
    }

    
    public List<Event> getEventsRandom() throws BaseException {
        try{
            List<Event> insights = eventDao.getEventsRandom();
            return insights;
        }
        catch (Exception exception) {
            logger.error("App - getEventsRandom Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetEventTagRes> getEventTags() throws BaseException {
        try{
            List<GetEventTagRes> getEventTagRes = eventDao.getEventTags();
            return getEventTagRes;
        } catch(Exception exception){
            logger.error("App - getEventTags Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<Event> getEventsByTag(String tag) throws BaseException {
        try{
            List<Event> insights = eventDao.getEventsByTag(tag);
            return insights;
        }
        catch (Exception exception) {
            logger.error("App - getEventsByTag Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<Event> getEventsByFilter(String tag, PostEventFilterReq req) throws BaseException {
        try{
            List<Event> insights = eventDao.getEventsByFilter(tag, req);
            return insights;
        }
        catch (Exception exception) {
            logger.error("App - getEventsByFilter Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyEventsById(int id, Event req) throws BaseException {
        try{
            Event event = new Event();
			event.setTitle(req.getTitle());
			event.setEventType(req.getEventType()); 
			event.setCharge(req.getCharge());
            System.out.println(">>>>>>>>>>>>>>>>>>"+req.getEventTag());
			event.setEventTag(req.getEventTag());

            String eventStatus = "ON";
            if (req.getEventStatus() != null){
                eventStatus = req.getEventStatus();
            }

			event.setEventStatus(eventStatus);
			event.setThumbnail(req.getThumbnail());
			event.setAuthor(req.getAuthor());
			event.setDetail(req.getDetail());
			event.setStartDate(req.getStartDate());
			event.setEndDate(req.getEndDate());

            eventDao.updateEvent(id, event);
        }
        catch (Exception exception) {
            logger.error("App - modifyEventsById Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteEventsById(int id) throws BaseException {
        try{
            eventDao.deleteEvent(id);
        }
        catch (Exception exception) {
            logger.error("App - modifyEventsById Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    
}
