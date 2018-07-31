package com.example.multimedia.service;

import com.example.multimedia.domain.*;
import com.example.multimedia.domain.returnMessage.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

public interface CommentService {
    /*
    * 评论功能
    * */
    String comment(String type,long objid,long userid,String content,long ruserid);
    public String replyR(String type,String content,long objid,long rcommentid,long userid,long ruserid);

    /*
    * 删除评论
    * */
    String deleteComment(String type,long docid,long commentid);  //删除评论
    String deleteComment(String type,long docid,long commentid,long relayid);  //删除评论下回复

    /*
    * 返回评论
    * */
    Map<DocCUser,List<DocRUser>> getDocComment(long docid, int pagenum);
    Map<ForumCUser,List<ForumRUser>> getForumComment(long docid, int pageNum);
    Map<VideoCUser,List<VideoRUser>> getVideoComment(long docid, int pageNum);
}
