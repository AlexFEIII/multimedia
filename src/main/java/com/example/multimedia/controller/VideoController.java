package com.example.multimedia.controller;

import com.example.multimedia.domain.Video;
import com.example.multimedia.domain.returnMessage.VideoUser;
import com.example.multimedia.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    //返回所有视频
    public List<VideoUser> getAllVideo(@RequestParam int pageNum,
                                       @RequestParam int size,
                                       @RequestParam String sort,
                                       @RequestParam String key){
        Sort.Direction direction = null;
        if (sort.equals("asc"))
            direction = Sort.Direction.ASC;
        else
            direction = Sort.Direction.DESC;
        return videoService.getAllVideo(pageNum,size,direction,key);
    }


    //返回单个视频
    @GetMapping("/{id}")
    public VideoUser getOneVideo(@PathVariable long id){
        return videoService.getOneVideo(id);
    }

    /*
    * 增加视频
    * */
    @PostMapping("/add")
    public String addVideo(@RequestParam String title,
                           @RequestParam(value = "summary") String summary,
                           @RequestParam String url,
                           @RequestParam(value = "image") MultipartFile image,
                           @RequestParam String type){
        return videoService.addVideo(title,summary,url,image,type);
    }

    /*
    * 删除视频
    * */
    @DeleteMapping("/delete/{id}")
    public String deleteVideo(@PathVariable long id){
        return videoService.deleteVideo(id);
    }

    /*
    * 修改视频
    * */
    @PostMapping("/change")
    public String changeVideo(@RequestParam long videoid,
                              @RequestParam(value = "title",required = false) String title,
                              @RequestParam(value = "image",required = false) MultipartFile image,
                              @RequestParam(value =  "summary",required = false) String summary,
                              @RequestParam(value = "url",required = false) String url,
                              @RequestParam(value = "type",required = false) String type){
        return videoService.changeVideo(videoid,title,image,summary,url,type);
    }
}
