package com.example.demo.src.insight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.insight.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.*;

@RestController
@RequestMapping("/insights")
public class InsightController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final InsightProvider insightProvider;
    @Autowired
    private final InsightService insightService;
    @Autowired
    private final JwtService jwtService;


    public InsightController(InsightProvider insightProvider, InsightService insightService, JwtService jwtService){
        this.insightProvider = insightProvider;
        this.insightService = insightService;
        this.jwtService = jwtService;
    }
    
    /**
     * 태그 별 인사이트 조회 API
     * [GET] /insights?tags=
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("") 
    public BaseResponse<List<Insight>> getInsights(@RequestParam String tags) throws BaseException {
        // Get interest tags
        try{
            List<Insight> insights = insightProvider.getInsight(tags);
            return new BaseResponse<>(insights);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 인사이트 태그 전체 조회 API
     * [GET] /insight
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/tags") 
    public BaseResponse<List<GetInsightTagRes>> getInsightTags() throws BaseException {
        // Get interest tags
        try{
            List<GetInsightTagRes> getComapnyTagRes = insightProvider.getInsightTags();
            return new BaseResponse<>(getComapnyTagRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    
}
