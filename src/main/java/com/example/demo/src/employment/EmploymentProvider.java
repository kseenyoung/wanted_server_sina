package com.example.demo.src.employment;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.employment.model.*;
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
public class EmploymentProvider {

    private final EmploymentDao employmentDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public EmploymentProvider(EmploymentDao employmentDao, JwtService jwtService) {
        this.employmentDao = employmentDao;
        this.jwtService = jwtService;
    }

    public List<Employment> getEmployments(String countries, String cities, String year1, String year2) throws BaseException {
        try{
            List<Employment> employments = employmentDao.getEmployments(countries, cities, year1, year2);
            return employments;
        } catch (Exception exception){
            logger.error("App - getEmployments Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public List<Employment> getEmployment(String categories, String countries, String cities, String year1, String year2) throws BaseException {
            try{
                List<Employment> employment = employmentDao.getEmployment(categories, countries, cities, year1, year2);
                return employment;
            } catch (Exception exception){
                logger.error("App - getEmployments Provider Error", exception);
                throw new BaseException(DATABASE_ERROR);
            }
    }
    
}
