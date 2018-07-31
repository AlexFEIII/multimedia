package com.example.multimedia.domain;

/*
* 登录信息表
* */
public class LoginMessage {
    private String status;
    private String msg;
    public LoginMessage(){}
    public LoginMessage(String status,String msg){
        this.status = status;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "LoginMessage{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
