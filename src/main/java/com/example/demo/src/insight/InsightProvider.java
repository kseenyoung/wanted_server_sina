package com.example.demo.src.insight;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.insight.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.hibernate.result.UpdateCountOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;


import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class InsightProvider {

    private final InsightDao insightDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public InsightProvider(InsightDao insightDao, JwtService jwtService) {
        this.insightDao = insightDao;
        this.jwtService = jwtService;
    }

    public List<GetInsightTagRes> getInsightTags() throws BaseException {
        try{
            List<GetInsightTagRes> getInsightTagRes = insightDao.getInsightTags();
            return getInsightTagRes;
        } catch(Exception exception){
            logger.error("App - getInsightTags Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<Insight> getInsight(String tag) throws BaseException {
        try{
            List<Insight> insights = insightDao.getInsights(tag);
            return insights;
        }
        catch (Exception exception) {
            logger.error("App - getInsight Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    
}
