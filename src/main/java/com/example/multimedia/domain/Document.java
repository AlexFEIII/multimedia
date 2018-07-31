package com.example.multimedia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/*
* 用户发表的文章表
* */
@Entity
public class Document {
    @Id
    @GeneratedValue
    private Long id;
    //发表用户的id
    private long userid;
    //文章标题
    private String title;
    //标题拼音
    private String tpinyin;
    //文章概要
    private String summary;
    //文章内容
    @Column(length = 65536)
    private String content;
    //文章图片
    private String image;
    //点赞个数
    private long upvotenum = 0;
    //评论个数
    private long commentnum = 0;
    //阅读个数
    private long sawnum = 0;
    //是否是互联网类别
    private boolean internet = false;
    //是否是法律
    private boolean law = false;
    //是否是医药
    private boolean medicine = false;
    //是否是经济
    private boolean economy = false;
    //是否是历史
    private boolean history = false;
    //是否是理工
    private boolean science = false;
    //是否是艺术
    private boolean art = false;
    //发表时间
    private Date date;

    public Document(){
        date = new Date();
    }

    public Document(String title,String summary,String content,String tpinyin,long userid){
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.tpinyin = tpinyin;;
        this.userid = userid;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", userid=" + userid +
                ", title='" + title + '\'' +
                ", tpinyin='" + tpinyin + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", upvotenum=" + upvotenum +
                ", commentnum=" + commentnum +
                ", sawnum=" + sawnum +
                ", internet=" + internet +
                ", law=" + law +
                ", medicine=" + medicine +
                ", economy=" + economy +
                ", history=" + history +
                ", science=" + science +
                ", art=" + art +
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTpinyin() {
        return tpinyin;
    }

    public void setTpinyin(String tpinyin) {
        this.tpinyin = tpinyin;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public long getUpvotenum() {
        return upvotenum;
    }

    public void setUpvotenum(long upvotenum) {
        this.upvotenum = upvotenum;
    }

    public long getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(long commentnum) {
        this.commentnum = commentnum;
    }

    public long getSawnum() {
        return sawnum;
    }

    public void setSawnum(long sawnum) {
        this.sawnum = sawnum;
    }

    public boolean isInternet() {
        return internet;
    }

    public void setInternet(boolean internet) {
        this.internet = internet;
    }

    public boolean isLaw() {
        return law;
    }

    public void setLaw(boolean law) {
        this.law = law;
    }

    public boolean isMedicine() {
        return medicine;
    }

    public void setMedicine(boolean medicine) {
        this.medicine = medicine;
    }

    public boolean isEconomy() {
        return economy;
    }

    public void setEconomy(boolean economy) {
        this.economy = economy;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }

    public boolean isScience() {
        return science;
    }

    public void setScience(boolean science) {
        this.science = science;
    }

    public boolean isArt() {
        return art;
    }

    public void setArt(boolean art) {
        this.art = art;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
