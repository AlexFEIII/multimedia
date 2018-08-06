package com.example.multimedia.service;

import com.example.multimedia.domain.MulUser;
import com.sun.corba.se.spi.ior.ObjectKey;

import java.util.List;
import java.util.Map;

public interface SearchService {
    /*
    * 搜索用户
    * */
    Map<String,Map<String,Object>> searchUser(String key);

    /*
    * 搜索文章
    * */
    Map<String,Map<String,Object>> searchDoc(String key);

    /*
    * 搜索视频
    * */
    Map<String,Map<String,Object>> searchVideo(String key);

    /*
    * 获取用户
    * */
    Map<String,Object> getUser();
}
