package com.example.multimedia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class SearchHistory {
    @Id
    @GeneratedValue
    private Long id;
    //搜索的用户
    private long userid;
    //搜索内容
    @Column(length = 65536)
    private String content;
    //时间
    private Date date;

    public SearchHistory(){}
    public SearchHistory(long userid,String content){
        this.userid = userid;
        this.content = content;
        this.date =   Date.from(LocalDateTime.now().plusWeeks(2).atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString() {
        return "SearchHistory{" +
                "id=" + id +
                ", userid=" + userid +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
