package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Report {
    @Id
    @GeneratedValue
    private Long id;
    //举报的用户id
    private long reportid;
    //举报的行为
    private String action;
    //行为描述
    private String content;
    //举报截图
    private String image;
    //申请举报的用户
    private long userid;
    //举报时间
    private Date date;

    public Report(){}

    public Report(long reportid,String action,String content,String image,long userid){
        this.reportid = reportid;
        this.action = action;
        this.content = content;
        this.image = image;
        this.userid = userid;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reportid=" + reportid +
                ", action='" + action + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", userid=" + userid +
                ", date=" + date +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getReportid() {
        return reportid;
    }

    public void setReportid(long reportid) {
        this.reportid = reportid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
