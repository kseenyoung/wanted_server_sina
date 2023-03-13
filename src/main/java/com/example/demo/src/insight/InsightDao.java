package com.example.demo.src.insight;


import com.example.demo.src.insight.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class InsightDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetInsightTagRes> getInsightTags() {
        String getInsightTagQuery = "select * from INTEREST_TAG";
        return this.jdbcTemplate.query(getInsightTagQuery,
                (rs,rowNum) -> new GetInsightTagRes(
                        rs.getString("classificationName"),
                        rs.getString("tagName"))
                );
    }

    public List<Insight> getInsights(String tag) {
        String getInsightQuery = "select I.id, I.title, I.contentType, I.author, I.contentUrl, I.explanation, I.thumbnail from INSIGHT I join INSIGHT_AND_INTEREST II on I.id = II.insightId join INTEREST_TAG T on II.tagId = T.id where tagName = ?";
        String getInsightParam = tag;
        
        return this.jdbcTemplate.query(getInsightQuery,
                (rs,rowNum) -> new Insight(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("contentType"),
                        rs.getString("contentUrl"),
                        rs.getString("author"),
                        rs.getString("explanation"),
                        rs.getString("thumbnail"))
                , getInsightParam);
    }
    
}
