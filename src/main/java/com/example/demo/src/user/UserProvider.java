package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.*;
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
public class UserProvider {

    private final UserDao userDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }

    public List<User> getUsers() throws BaseException{
        try{
            List<User> users = userDao.getUsers();
            return users;
        }
        catch (Exception exception) {
            logger.error("App - getUserRes Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetUserRes> getUsersByEmail(String email) throws BaseException {
        try {
            List<GetUserRes> getUsersRes = userDao.getUsersByEmail(email);
            return getUsersRes;
        } catch (Exception exception) {
            logger.error("App - getUsersByEmail Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public User getUser(int id) throws BaseException, EmptyResultDataAccessException {
        try {
            User user = userDao.getUser(id);
            return user;
        } catch (EmptyResultDataAccessException exception){
            logger.error("not match id", exception);
            throw new BaseException(RESULT_ACTUAL_ZERO);
        } 
        catch (Exception exception) {
            logger.error("App - getUser Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkEmail(String email) throws BaseException{
        try{
            return userDao.checkEmail(email);
        } catch (EmptyResultDataAccessException exception){
        logger.error("not match id", exception);
            throw new BaseException(RESULT_ACTUAL_ZERO);
        } catch (Exception exception){
            logger.error("App - checkEmail Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException {
         try {
            //입력받은 이메일에 해당하는 유저 정보
             User user = userDao.getPwd(postLoginReq);
             //System.out.println("-------" + user.getEmail());

            //password 복호화
            String encryptPwd;
            try {
                encryptPwd = new SHA256().encrypt(postLoginReq.getPassword());
            } catch (Exception exception) {
                logger.error("App - logIn Provider Encrypt Error", exception);
                throw new BaseException(PASSWORD_DECRYPTION_ERROR);
            }

            // 비밀번호 일치 확인 & jwt 발급
             if (user.getPassword().equals(encryptPwd)) {
                 int userIdx = user.getID();
                 String jwt = jwtService.createJwt(userIdx);
                 return new PostLoginRes(userIdx, jwt);
             }
             System.out.println("-------is this exe???");
             throw new BaseException(FAILED_TO_LOGIN);
        } catch (Exception exception) {
            logger.error("App - logIn Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
