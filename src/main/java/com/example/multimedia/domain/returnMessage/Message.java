package com.example.multimedia.domain.returnMessage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
* 通知信息表
* */
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    //用户编号
    private long userid;
    //通知内容
    @Column(length = 1024)
    private String message;
    //是否已读
    private boolean issaw;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userid=" + userid +
                ", message='" + message + '\'' +
                ", issaw=" + issaw +
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIssaw() {
        return issaw;
    }

    public void setIssaw(boolean issaw) {
        this.issaw = issaw;
    }
}
