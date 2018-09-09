package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class Code {
    @Id
    @GeneratedValue
    private Long id;
    private String phoneNum;
    private String code;
    private Date date;

    public Code(){}
    public Code(String phoneNum,String code){
        this.phoneNum = phoneNum;
        this.code = code;
        this.date = Date.from(LocalDateTime.now().plusMinutes(2).atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", phoneNum='" + phoneNum + '\'' +
                ", code='" + code + '\'' +
                ", date=" + date +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
