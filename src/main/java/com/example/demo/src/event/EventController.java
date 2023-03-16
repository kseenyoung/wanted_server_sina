package com.example.demo.src.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.event.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.*;

@RestController
@RequestMapping("/events")
public class EventController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final EventProvider eventProvider;
    @Autowired
    private final EventService eventService;
    @Autowired
    private final JwtService jwtService;


    public EventController(EventProvider eventProvider, EventService eventService, JwtService jwtService){
        this.eventProvider = eventProvider;
        this.eventService = eventService;
        this.jwtService = jwtService;
    }
    
    /**
     * 이벤트 전체 조회 API
     * [GET] /events?tags
     * @return BaseResponse<List<Event>>
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<Event>> getEvents(@RequestParam(required = false) String tags) throws BaseException {
        try{
            List<Event> events = eventProvider.getEventsByTag(tags);
            return new BaseResponse<>(events);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 필터링 이벤트 조회 API
     * [POST] /events/:tags
     * @return BaseResponse<List<Event>>
     */
    @ResponseBody
    @GetMapping("/{tags}")
    public BaseResponse<List<Event>> getEventsByFilter(@PathVariable String tags, @RequestBody PostEventFilterReq req) throws BaseException {
        try{
            List<Event> events = eventProvider.getEventsByFilter(tags,req);
            return new BaseResponse<>(events);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 이벤트 추가 API
     * [POST] /events/append
     * @return BaseResponse<List<Event>>
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<String> createEvent(@RequestBody Event req) throws BaseException {
        try{
            eventService.createEvent(req);
            return new BaseResponse<>("");
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 메인화면 랜덤 이벤트 조회 API
     * [GET] /events/main
     * @return BaseResponse<List<Event>>
     */
    @ResponseBody
    @GetMapping("/main")
    public BaseResponse<List<Event>> getEventsRandom() throws BaseException {
        // Get interest tags
        try{
            List<Event> events = eventProvider.getEventsRandom();
            return new BaseResponse<>(events);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 이벤트 태그 전체 조회 API
     * [GET] /events/tags
     * @return BaseResponse<List<GetEventTagRes>>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/tags")
    public BaseResponse<List<GetEventTagRes>> getEventTags() throws BaseException {
        // Get interest tags
        try{
            List<GetEventTagRes> getComapnyTagRes = eventProvider.getEventTags();
            return new BaseResponse<>(getComapnyTagRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

        /**
     * 이벤트 갱신 API
     * [PATCH] /events/:id
     * @return BaseResponse<List<GetEventTagRes>>
     */
    // Path-variable
    @ResponseBody
    @PatchMapping("/{id}")
    public BaseResponse<String> getEventTags(@PathVariable int id, @RequestBody Event req) throws BaseException {
        // Get interest tags
        try{
            eventProvider.modifyEventsById(id, req);
            return new BaseResponse<>("");
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

        /**
     * 특정 이벤트 삭제 API
     * [PATCH] /events/:id
     * @return BaseResponse<List<GetEventTagRes>>
     */
    // Path-variable
    @ResponseBody
    @PatchMapping("/{id}/status")
    public BaseResponse<String> getEventTags(@PathVariable int id) throws BaseException {
        // Get interest tags
        try{
            eventProvider.deleteEventsById(id);
            return new BaseResponse<>("");
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    
}
