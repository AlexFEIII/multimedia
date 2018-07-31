package com.example.multimedia.service;

import com.example.multimedia.domain.MulUser;
import com.sun.corba.se.spi.ior.ObjectKey;

import java.util.List;
import java.util.Map;

public interface SearchService {
    /*
    * 搜索用户
    * */
    Map<String,Map<String,Object>> searchUser(String key,String obj,String sort);

    /*
    * 搜索文章
    * */
    Map<String,Map<String,Object>> searchDoc(String type,String key,String obj,String sort);

    /*
    * 搜索视频
    * */
    Map<String,Map<String,Object>> searchVideo(String key,String obj,String sort);

    /*
    * 获取用户
    * */
    Map<String,Object> getUser();
}
