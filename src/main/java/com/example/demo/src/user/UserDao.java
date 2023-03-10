package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> getUsers(){
        String getUsersQuery = "select * from User";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new User(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phoneNational"),
                        rs.getString("phoneNumber"),
                        rs.getString("imgUrl"),
                        rs.getInt("marketingAgreement"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from UserInfo where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password")),
                getUsersByEmailParams);
    }

    public User getUser(int id){
        String getUserQuery = "select * from User where id = ? and status = 'ACTIVE'";
        int getUserParams = id;

        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new User(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phoneNational"),
                    rs.getString("phoneNumber"),
                    rs.getString("imgUrl"),
                    rs.getInt("marketingAgreement")),
                getUserParams);
    }
    

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into User (name, email, password, phoneNational, phoneNumber, marketingAgreement) VALUES (?,?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getName(), postUserReq.getEmail(), postUserReq.getPassword(), postUserReq.getPhoneNational(), postUserReq.getPhoneNumber(), postUserReq.getMarketingAgreement()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from User where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update UserInfo set userName = ? where userIdx = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select password, email, name, ID, phoneNational, phoneNumber, imgUrl, marketingAgreement from User where email = ? and status = 'ACTIVE'";
        String getPwdParams = postLoginReq.getEmail();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phoneNational"),
                        rs.getString("phoneNumber"),
                        rs.getString("imgUrl"),
                        rs.getInt("marketingAgreement")),
                getPwdParams
                );

    }


    public int modifyUserPassword(PatchUserPasswordReq patchUserPasswordReq) {
        String modifyUserPasswordQuery = "update User set password = ? where id = ?";
        Object[] modifyUserPasswordParams = new Object[]{patchUserPasswordReq.getNewPassword(), patchUserPasswordReq.getId()};

        return this.jdbcTemplate.update(modifyUserPasswordQuery, modifyUserPasswordParams);
    }

    // public int checkPassword(PatchUserPasswordReq patchUserPasswordReq) {
    //     String checkPasswordQuery = "select exists(select password from User where password = ? and id = ?)";
    //     String checkPasswordParams = new Object[]{patchUserPasswordReq.getOldPassword(), patchUserPasswordReq.getId()};
    //     return this.jdbcTemplate.queryForObject(checkPasswordQuery,
    //             int.class,
    //             checkPasswordParams);
    // }
}
