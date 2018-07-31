package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.MulUser;
import com.example.multimedia.domain.Video;
import com.example.multimedia.service.ServiceImpl.VideoServiceImpl;

public class VideoUser {
    private Video video;
    private MulUser mulUser;
    public VideoUser(){}
    public VideoUser(Video video,MulUser mulUser){
        this.video = video;
        this.mulUser = mulUser;
    }

    @Override
    public String toString() {
        return "VideoUser{" +
                "video=" + video +
                ", mulUser=" + mulUser +
                '}';
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public MulUser getMulUser() {
        return mulUser;
    }

    public void setMulUser(MulUser mulUser) {
        this.mulUser = mulUser;
    }
}
