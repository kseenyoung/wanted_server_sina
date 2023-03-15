package com.example.demo.src.company;


import com.example.demo.src.company.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CompanyDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetCompanyTagRes> getCompanyTags() {
        String getCompaniesQuery = "select * from COMPANY_TAG";
        return this.jdbcTemplate.query(getCompaniesQuery,
                (rs,rowNum) -> new GetCompanyTagRes(
                        rs.getString("classificationName"),
                        rs.getString("tagName"))
                );
    }

    public int createCompany(Company company) {
        String createCompanyQuery = "insert into COMPANY(name, explanation, totalEmployees, status) values(?,?,?,?)";
        Object[] createCompanyParams = 
		new Object[]{
			company.getName(), 
			company.getExplanation(),
            company.getTotalEmployees(),
			"ACTIVE"
			};

		this.jdbcTemplate.update(createCompanyQuery, createCompanyParams);

		String lastInserIdQuery = "select last_insert_id()";
	    return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }
    
}
