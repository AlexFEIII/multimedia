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
    @GetMapping(value = "/user",params = "key")
    public Map<String,Map<String,Object>> searchUser(String key){
        return searchService.searchUser(key);
    }

    /*
    * 搜索文章
    * 文章类型两种：doc/forum
    * 可根据id，热度（sawnum）
    * */
    @GetMapping(value = "/doc",params = "key")
    public Map<String,Map<String,Object>> searchDoc(String key){
        return searchService.searchDoc(key);
    }

    /*
    * 搜索视频
    * */
    @GetMapping(value = "/video",params = "key")
    public Map<String,Map<String,Object>> searchVideo(String key){
        System.out.println(searchService.searchVideo(key));
        return searchService.searchVideo(key);
    }

    /*
    * 得到用户
    * */
    @GetMapping("getUser")
    public Map<String,Object> getUser(){
        return searchService.getUser();
    }
}
