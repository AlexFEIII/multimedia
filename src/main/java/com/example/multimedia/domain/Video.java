package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/*
* 视频表
* */
@Entity
public class Video {
    @Id
    @GeneratedValue
    private Long id;
    //视频链接
    private String url;
    //视频标题
    private String title;
    //视频界面
    private String image;
    // 视频简介
    private String summary;
    //标题拼音
    private String tpinyin;
    //上传视频的用户
    private long userid;
    //点赞个数
    private long upvotenum;
    //评论个数
    private long commentnum;
    //视频类别
    private String type;
    //时间
    private Date date;
    //视频浏览个数
    private long sawnum;

    public Video(){
        this.date = new Date();
    }

    public Video(String title,String tpinyin,String image,String summary,String url,long userid,String type){
        this.title = title;
        this.tpinyin = tpinyin;
        this.image = image;
        this.summary = summary;
        this.url = url;
        this.userid = userid;
        this.type = type;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", summary='" + summary + '\'' +
                ", tpinyin='" + tpinyin + '\'' +
                ", userid=" + userid +
                ", upvotenum=" + upvotenum +
                ", commentnum=" + commentnum +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", sawnum=" + sawnum +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTpinyin() {
        return tpinyin;
    }

    public void setTpinyin(String tpinyin) {
        this.tpinyin = tpinyin;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getSawnum() {
        return sawnum;
    }

    public void setSawnum(long sawnum) {
        this.sawnum = sawnum;
    }
}
