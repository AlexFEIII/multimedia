package com.example.multimedia.service;

import com.example.multimedia.domain.Forum;
import com.example.multimedia.domain.returnMessage.ForumUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ForumService {
    /*
     * 获得所有文章
     * */
    List<ForumUser> getAllForum(int pageNum, int size, Sort.Direction direction, String key);

    //获得一篇文章
    ForumUser getOneForum(long id);

    //获得我的文章
    List<ForumUser> getMineForum();

    /*
     * 增加文章
     * */
    String addForum(String title, String summary, String content, MultipartFile image, String type);

    /*
     * 修改文章
     * */
    String changeForum(long forumid,String title, String summary, String content, MultipartFile image, String type);

    /*
    * 设置最佳评论
    * */
    String best(long forumid,long commentid);

    /*
    * 删除
    * */
    String deleteForum(long id);
}
