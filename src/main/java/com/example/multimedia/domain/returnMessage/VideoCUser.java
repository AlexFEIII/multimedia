package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.MulUser;
import com.example.multimedia.domain.VideoComment;

//视频评论 + 用户
public class VideoCUser {
    private VideoComment videoComment;
    private MulUser mulUser;

    public VideoCUser(){}
    public VideoCUser(VideoComment videoComment,MulUser mulUser){
        this.videoComment = videoComment;
        this.mulUser = mulUser;
    }

    @Override
    public String toString() {
        return "VideoCUser{" +
                "videoComment=" + videoComment +
                ", mulUser=" + mulUser +
                '}';
    }

    public VideoComment getVideoComment() {
        return videoComment;
    }

    public void setVideoComment(VideoComment videoComment) {
        this.videoComment = videoComment;
    }

    public MulUser getMulUser() {
        return mulUser;
    }

    public void setMulUser(MulUser mulUser) {
        this.mulUser = mulUser;
    }
}
