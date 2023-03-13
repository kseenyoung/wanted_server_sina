package com.example.demo.src.company;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.company.model.*;
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
public class CompanyProvider {

    private final CompanyDao companyDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CompanyProvider(CompanyDao companyDao, JwtService jwtService) {
        this.companyDao = companyDao;
        this.jwtService = jwtService;
    }

    public List<GetCompanyTagRes> getCompanyTags() throws BaseException {
        try{
            List<GetCompanyTagRes> getCompanyTagRes = companyDao.getCompanyTags();
            return getCompanyTagRes;
        } catch(Exception exception){
            logger.error("Company Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    
}
