package com.example.demo.src.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.company.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CompanyProvider companyProvider;
    @Autowired
    private final CompanyService companyService;
    @Autowired
    private final JwtService jwtService;



    public CompanyController(CompanyProvider companyProvider, CompanyService companyService, JwtService jwtService){
        this.companyProvider = companyProvider;
        this.companyService = companyService;
        this.jwtService = jwtService;
    }

    /**
     * 회사 태그 전체 조회 API
     * [GET] /companies/tags
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/tags") 
    public BaseResponse<List<GetCompanyTagRes>> getCompanyTags() {
        // Get Users
        try{
            List<GetCompanyTagRes> getComapnyTagRes = companyProvider.getCompanyTags();
            return new BaseResponse<>(getComapnyTagRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

        /**
     * 회사 추가 API
     * [POST] /companies
     * @return BaseResponse<List<Event>>
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<String> createEvent(@RequestBody Company req) throws BaseException {
        if (req.getName() == null){
            throw new BaseException(POST_EMPTY_COMPANY_NAME);
        }
        if (req.getExplanation() == null){
            throw new BaseException(POST_EMPTY_COMPANY_EXPLANAION);
        }
        try{
            companyService.createCompany(req);
            return new BaseResponse<>("");
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    
}
