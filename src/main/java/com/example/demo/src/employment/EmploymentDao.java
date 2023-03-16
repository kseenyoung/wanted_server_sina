package com.example.demo.src.employment;


import com.example.demo.src.employment.model.Employment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EmploymentDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Employment> getEmployments(String countries, String cities, String year1, String year2) {
        String getEmploymentQuery = "select * from EMPLOYMENT where country = ? and city = ? and career between ? and ? and status = 'ACTIVE'";
        Object[] getEmploymentParam = new Object[]{countries, cities, year1, year2};

        return this.jdbcTemplate.query(getEmploymentQuery,
        (rs,rowNum) -> new Employment(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getString("subCategory"),
                rs.getString("country"),
                rs.getString("city"),
                rs.getInt("compensation"),
                rs.getString("imageUrl"))
                , getEmploymentParam
        );
    }


    public List<Employment> getEmployment(String categories, String countries, String cities, String year1, String year2) {
        String getEmploymentQuery = "select * from EMPLOYMENT where category = ? and country = ? and city = ? and career between ? and ? and status = 'ACTIVE'";
        Object[] getEmploymentParam = new Object[]{categories, countries, cities, year1, year2};

        return this.jdbcTemplate.query(getEmploymentQuery,
        (rs,rowNum) -> new Employment(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getString("subCategory"),
                rs.getString("country"),
                rs.getString("city"),
                rs.getInt("compensation"),
                rs.getString("imageUrl"))
                , getEmploymentParam
        );
    }
    
}
