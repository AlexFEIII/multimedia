package com.example.multimedia.service;

import com.example.multimedia.domain.Video;
import com.example.multimedia.domain.returnMessage.VideoUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    //获得所有视频
    List<VideoUser> getAllVideo(int pageNum, int size, Sort.Direction direction, String key);

    //获得单个视频
    VideoUser getOneVideo(long id);

    //获得我的视频
    List<Video> getMineVideo();

    /*
    * 增加视频
    * */
    String addVideo(String title, String summary, String url, MultipartFile image,String type);

    /*
    * 删除视频
    * */
    String deleteVideo(long id);

    /*
    * 修改视频
    * */
    String changeVideo(long videoid,String title,MultipartFile image,String summary,String url,String type);
}
