package com.example.multimedia.domain.returnMessage;

import java.util.List;
import java.util.Map;

public class VCView {
    private int totalPage;
    private VideoCUser videoCUser;
    private List<VideoRUser> videoRUsers;

    public VCView(){}
    public VCView(int totalPage,VideoCUser videoCUser, List<VideoRUser> videoRUsers){
        this.totalPage = totalPage;
        this.videoCUser = videoCUser;
        this.videoRUsers = videoRUsers;
    }

    @Override
    public String toString() {
        return "VCView{" +
                "totalPage=" + totalPage +
                ", videoCUser=" + videoCUser +
                ", videoRUsers=" + videoRUsers +
                '}';
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public VideoCUser getVideoCUser() {
        return videoCUser;
    }

    public void setVideoCUser(VideoCUser videoCUser) {
        this.videoCUser = videoCUser;
    }

    public List<VideoRUser> getVideoRUsers() {
        return videoRUsers;
    }

    public void setVideoRUsers(List<VideoRUser> videoRUsers) {
        this.videoRUsers = videoRUsers;
    }
}
