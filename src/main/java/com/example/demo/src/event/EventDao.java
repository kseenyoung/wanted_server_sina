package com.example.demo.src.event;


import com.example.demo.src.event.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EventDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetEventTagRes> getEventTags() {
        String getInsightTagQuery = "select * from EVENT_TAG";
        return this.jdbcTemplate.query(getInsightTagQuery,
                (rs,rowNum) -> new GetEventTagRes(
                        rs.getString("classificationName"),
                        rs.getString("tagName"))
                );
    }

    public List<Event> getEventsRandom() {
        String getInsightQuery = "select * from EVENT where status = 'ACTIVE' and eventStatus = 'ON' order by RAND() limit 9";
        
        return this.jdbcTemplate.query(getInsightQuery,
                (rs,rowNum) -> new Event(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("eventType"),
                        rs.getString("charge"),
                        rs.getString("EventTag"),
                        rs.getString("startDate"),
                        rs.getString("endDate"),
                        rs.getString("author"),
                        rs.getString("eventStatus"),
                        rs.getString("thumbnail"),
                        rs.getString("detail"),
                        rs.getString("location"),
                        rs.getString("status"))
                );
    }

    public List<Event> getEventsByTag(String tag) {
        String getInsightQuery = "select * from EVENT where status = 'ACTIVE' and EventTag like ?";
        String getInsightParam = tag == null || "ALL".equals(tag) ? "%%" : "%" + tag + "%";
        
        return this.jdbcTemplate.query(getInsightQuery,
                (rs,rowNum) -> new Event(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("eventType"),
                        rs.getString("charge"),
                        rs.getString("EventTag"),
                        rs.getString("startDate"),
                        rs.getString("endDate"),
                        rs.getString("author"),
                        rs.getString("eventStatus"),
                        rs.getString("thumbnail"),
                        rs.getString("detail"),
                        rs.getString("location"),
                        rs.getString("status"))
                , getInsightParam);
    }

    public List<Event> getEventsByFilter(String tag, PostEventFilterReq req) {
        String getEventQuery = "select * from EVENT where status = 'ACTIVE' and EventTag like ? ";
        
        if(!"ALL".equals(req.getEventTypeCd())){ //유형
            getEventQuery += "and eventType = '"+req.getEventTypeNm()+"' ";
        }

        if("FREE".equals(req.getCharge())){ //유/무료
            getEventQuery += "and charge = 'FREE' ";
        }else if("PAY".equals(req.getCharge())){
            getEventQuery += "and charge = 'PAY' ";
        }
        
        if(req.getEventTag() != null){ //태그
            for(int i=0;i<req.getEventTag().length;i++){
                getEventQuery += "and EventTag like '%"+req.getEventTag()[i]+"%' ";
            }
        }

        if(req.getSort() != null){ //정렬
            switch(req.getSort()){
                case "1" : getEventQuery += "order by endDate asc"; break;
                // case "2" : getEventQuery += "order by recommend desc"; break;
                case "3" : getEventQuery += "order by createAt desc"; break;
            }
        }

        String getEventParam = "%"+tag+"%";
        
        return this.jdbcTemplate.query(getEventQuery,
                (rs,rowNum) -> new Event(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("eventType"),
                        rs.getString("charge"),
                        rs.getString("EventTag"),
                        rs.getString("startDate"),
                        rs.getString("endDate"),
                        rs.getString("author"),
                        rs.getString("eventStatus"),
                        rs.getString("thumbnail"),
                        rs.getString("detail"),
                        rs.getString("location"),
                        rs.getString("status"))
                , getEventParam);
    }
    
    public int createEvent(Event req){
        String createEventQuery = "insert into EVENT(title,eventType,charge,EventTag,eventStatus,thumbnail,author,detail,startDate,endDate,status) values(?,?,?,?,?,?,?,?,?,?,?)";
        Object[] createEventParams = 
		new Object[]{
			req.getTitle(), 
			req.getEventType(), 
			req.getCharge(),
			req.getEventTag(),
			req.getEventStatus(),
			req.getThumbnail(),
			req.getAuthor(),
			req.getDetail(),
			req.getStartDate(),
			req.getEndDate(),
			"ACTIVE"
			};

		this.jdbcTemplate.update(createEventQuery, createEventParams);

		String lastInserIdQuery = "select last_insert_id()";
	return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int updateEvent(Event req){
        String modifyEventQuery = "update EVENT set title = ?, eventType = ?, charge = ?, EventTag = ? eventStatus = ?, thumbnail = ?, author = ?, detail = ? startDate = ?, endDate = ? where id = ?";
        Object[] modifyEventParams = 
		new Object[]{
			req.getTitle(), 
			req.getEventType(), 
			req.getCharge(),
            req.getEventTag(),
			req.getEventStatus(),
			req.getThumbnail(),
			req.getAuthor(),
			req.getDetail(),
			req.getStartDate(),
			req.getEndDate(),
            req.getId()
			};
            return this.jdbcTemplate.update(modifyEventQuery,modifyEventParams);
    }

    public int deleteEvent(int evtId){
        String modifyEventQuery = "update EVENT set status = 'DELETE' where id = ?";
        int modifyEventParams = evtId;
            return this.jdbcTemplate.update(modifyEventQuery,modifyEventParams);
    }

}
