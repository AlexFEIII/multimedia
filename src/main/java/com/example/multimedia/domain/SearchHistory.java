package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class SearchHistory {
    @Id
    @GeneratedValue
    private Long id;
    //搜索的用户
    private long userid;
    //搜索内容
    private String key;
    //时间
    private Date date;

    public SearchHistory(){}
    public SearchHistory(long userid,String key){
        this.userid = userid;
        this.key = key;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "SearchHistory{" +
                "id=" + id +
                ", userid=" + userid +
                ", key='" + key + '\'' +
                ", date=" + date +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
