package com.example.multimedia.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;

/*
* 用户信息表
* */
@Entity
@Table(indexes = {
        @Index(name = "keyname",columnList = "username",unique = true)
})
public class MulUser{
    @Id
    @GeneratedValue
    private Long id;
    //用户名/手机号
    private String username;
    //用户名拼音
    @JsonIgnore
    private String upinyin;
    //密码
    @JsonIgnore
    private String password;
    private String headimage;
    //邮箱
    private String email;
    //性别
    private int sex; //1:man ,2:woman , 0:unknown
    //个人简介
    private String personality;
    //地址
    private String address;
    //QQ
    private String qq;
    //昵称
    private String nickname;
    //职业
    private String job;
    //个人网站
    private String weburl;
    //角色
    @JsonIgnore
    private String role;
    //权限
    @JsonIgnore
    private String power;

    public MulUser(){}
    public MulUser(String username,String upinyin,String password,String role){
        this.username = username;
        this.upinyin = upinyin;
        this.password = password;
        this.role = role;
        headimage = "../img/14.png";
    }

    @Override
    public String toString() {
        return "MulUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", upinyin='" + upinyin + '\'' +
                ", password='" + password + '\'' +
                ", headimage='" + headimage + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", personality='" + personality + '\'' +
                ", address='" + address + '\'' +
                ", qq=" + qq +
                ", nickname=" + nickname +
                ", job='" + job + '\'' +
                ", weburl='" + weburl + '\'' +
                ", role='" + role + '\'' +
                ", power='" + power + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUpinyin() {
        return upinyin;
    }

    public void setUpinyin(String upinyin) {
        this.upinyin = upinyin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String phone) {
        this.nickname = nickname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}