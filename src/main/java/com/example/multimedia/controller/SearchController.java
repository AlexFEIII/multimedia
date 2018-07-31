package com.example.multimedia.controller;

import com.example.multimedia.service.SearchService;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /*
    * 搜索用户
    * key：关键词  obj：排序的字段  sort：顺序(asc/desc)
    * @timestamp 根据时间
    * */
    @PostMapping("/user")
    public Map<String,Map<String,Object>> searchUser(@RequestParam("key") String key,
                                                  @RequestParam String sort){
        return searchService.searchUser(key,"id",sort);
    }

    /*
    * 搜索文章
    * 文章类型两种：doc/forum
    * 可根据id，热度（sawnum）
    * */
    @PostMapping("/doc")
    public Map<String,Map<String,Object>> searchDoc(@RequestParam String type,
                                                    @RequestParam String key,
                                                    @RequestParam String obj,
                                                    @RequestParam String sort){
        return searchService.searchDoc(type,key,obj,sort);
    }

    /*
    * 搜索视频
    * */
    @PostMapping("/video")
    public Map<String,Map<String,Object>> searchVideo(@RequestParam String key,
                                                      @RequestParam String obj,
                                                      @RequestParam String sort){
        return searchService.searchVideo(key,obj,sort);
    }

    /*
    * 得到用户
    * */
    @GetMapping("getUser")
    public Map<String,Object> getUser(){
        return searchService.getUser();
    }
}
