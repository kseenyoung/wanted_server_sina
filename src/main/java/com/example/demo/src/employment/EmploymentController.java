package com.example.demo.src.employment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.employment.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.*;

@RestController
@RequestMapping("/employments")
public class EmploymentController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final EmploymentProvider employmentProvider;
    @Autowired
    private final EmploymentService employmentService;
    @Autowired
    private final JwtService jwtService;


    public EmploymentController(EmploymentProvider employmentProvider, EmploymentService employmentService, JwtService jwtService){
        this.employmentProvider = employmentProvider;
        this.employmentService = employmentService;
        this.jwtService = jwtService;
    }

        /**
     * 전체 채용 정보 조회 API
     * [GET] /employments?countries=&cities=&year1=&year2=
     * @return BaseResponse<GetUserRes>
     */
    @ResponseBody
    @GetMapping("") 
    public BaseResponse<List<Employment>> getEmployments(@RequestParam(required = false) String categories, @RequestParam String countries, @RequestParam String cities, @RequestParam String year1, @RequestParam String year2) throws BaseException {
        // Get emplyment
        if (categories != null){
            try{
                List<Employment> employment = employmentProvider.getEmployment(categories, countries, cities, year1, year2);
                return new BaseResponse<>(employment);
            } catch(BaseException exception){
                return new BaseResponse<>((exception.getStatus()));
            }
        }
        // Get All emplyments
        else{
            try{
                List<Employment> employment = employmentProvider.getEmployments(countries, cities, year1, year2);
                return new BaseResponse<>(employment);
            } catch(BaseException exception){
                return new BaseResponse<>((exception.getStatus()));
        }
        }

    }

    
}
