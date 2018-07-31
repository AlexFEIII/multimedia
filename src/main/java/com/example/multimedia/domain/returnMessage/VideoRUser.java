package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.MulUser;
import com.example.multimedia.domain.VideoRelay;

//视频回复 + 用户
public class VideoRUser {
    private VideoRelay videoRelay;
    private MulUser mulUser;

    public VideoRUser(){}
    public VideoRUser(VideoRelay videoRelay,MulUser mulUser){
        this.videoRelay = videoRelay;
        this.mulUser = mulUser;
    }

    @Override
    public String toString() {
        return "VideoRUser{" +
                "videoRelay=" + videoRelay +
                ", mulUser=" + mulUser +
                '}';
    }

    public VideoRelay getVideoRelay() {
        return videoRelay;
    }

    public void setVideoRelay(VideoRelay videoRelay) {
        this.videoRelay = videoRelay;
    }

    public MulUser getMulUser() {
        return mulUser;
    }

    public void setMulUser(MulUser mulUser) {
        this.mulUser = mulUser;
    }
}
