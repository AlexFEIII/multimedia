package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.*;
import com.example.multimedia.domain.returnMessage.*;
import com.example.multimedia.repository.*;
import com.example.multimedia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Collection;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private DocCommentRepository docCommentRepository;
    @Autowired
    private ForumCommentRepository forumCommentRepository;
    @Autowired
    private VideoCommentRepository videoCommentRepository;
    @Autowired
    private DocRelayRepository docRelayRepository;
    @Autowired
    private ForumRelayRepository forumRelayRepository;
    @Autowired
    private VideoRelayRepository videoRelayRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private VideoRepository videoRepository;

    SensitivewordFilter sensitivewordFilter = new SensitivewordFilter();

    /*
    * 评论功能
    * */
    @Override
    public String comment(String type, long objid, long userid, String content, long ruserid) {
        content = sensitivewordFilter.turnWord(content);
        if (type.equals("doc")){
            docCommentRepository.save(new DocComment(deleteHTML(content),objid,userid,ruserid));
        }else if (type.equals("forum")){
            forumCommentRepository.save(new ForumComment(deleteHTML(content),objid,userid,ruserid));
        }else if (type.equals("video")){
            videoCommentRepository.save(new VideoComment(deleteHTML(content),objid,userid,ruserid));
        }else if (type.equals("docR")){
            docRelayRepository.save(new DocRelay(deleteHTML(content),objid,userid,ruserid));
        }else if (type.equals("forumR")){
            forumRelayRepository.save(new ForumRelay(deleteHTML(content),objid,userid,ruserid));
        }else if (type.equals("videoR")){
            videoRelayRepository.save(new VideoRelay(deleteHTML(content),objid,userid,ruserid));
        }else{
            return null;
        }
        return content;
    }

    /*
    * 回复 回复
    * */
    @Override
    public String replyR(String type,String content,long objid,long rcommentid,long userid,long ruserid){
        content = sensitivewordFilter.turnWord(content);
        if (type.equals("docRR")){
            docRelayRepository.save(new DocRelay(deleteHTML(content),objid,rcommentid,userid,ruserid));
        }else if (type.equals("forumRR")){
            forumRelayRepository.save(new ForumRelay(deleteHTML(content),objid,rcommentid,userid,ruserid));
        }else if (type.equals("videoRR")){
            videoRelayRepository.save(new VideoRelay(deleteHTML(content),objid,rcommentid,userid,ruserid));
        }else{
            return null;
        }
        return content;
    }

    /*
    * 删除评论
    * */
    @Override
    public String deleteComment(String type,long docid,long commentid,long relayid) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        if (type.equals("doc")){
            //如果文章是自己的可以删除||如果评论是自己的，可以删除||如果被回复的用户是自己，可以删除
            if (userRepository.findOne(documentRepository.findOne(docid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(docCommentRepository.findOne(commentid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(docRelayRepository.findOne(relayid).getUserid()).getUsername().equals(username)){
                docRelayRepository.delete(relayid);
                return "Y";
            }
            return "N";
        }else if (type.equals("forum")){
            if (userRepository.findOne(forumRepository.findOne(docid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(forumCommentRepository.findOne(commentid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(forumRelayRepository.findOne(relayid).getUserid()).getUsername().equals(username)){
                forumRelayRepository.delete(relayid);
                return "Y";
            }
            return "N";
        }else {
            if (userRepository.findOne(videoRepository.findOne(docid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(videoCommentRepository.findOne(commentid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(videoRelayRepository.findOne(relayid).getUserid()).getUsername().equals(username)){
                videoRelayRepository.delete(relayid);
                return "Y";
            }
            return "N";
        }
    }

    /**
     * @param type 类型，doc，forum，video
     * @param docid 该类型中文章的id
     * @param commentid 文章中评论id;
     */
    @Override
    public String deleteComment(String type,long docid, long commentid) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        //如果文档是自己的可以删||如果评论是自己的可以删
        if (type.equals("doc")){
            //如果文章是自己的可以删除||如果评论是自己的，可以删除||如果被回复的用户是自己，可以删除
            if (userRepository.findOne(documentRepository.findOne(docid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(docCommentRepository.findOne(commentid).getUserid()).getUsername().equals(username)){
                docCommentRepository.delete(commentid);
                return "Y";
            }
            return "N";
        }else if (type.equals("forum")){
            if (userRepository.findOne(forumRepository.findOne(docid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(forumCommentRepository.findOne(commentid).getUserid()).getUsername().equals(username)){
                forumCommentRepository.delete(commentid);
                return "Y";
            }
            return "N";
        }else {
            if (userRepository.findOne(videoRepository.findOne(docid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(videoCommentRepository.findOne(commentid).getUserid()).getUsername().equals(username)){
                videoCommentRepository.delete(commentid);
                return "Y";
            }
            return "N";
        }
    }

    //返回评论*3
    @Override
    public Map<DocCUser, List<DocRUser>> getDocComment(long docid, int pagenum) {
        LinkedHashMap<DocCUser,List<DocRUser>> map = new LinkedHashMap<>();
        List<DocComment> list = null;
        try{
            list = docCommentRepository.findByDocid(docid).subList((pagenum-1)*5,pagenum*5);
        }catch (Exception e){
            try {
                list = docCommentRepository.findByDocid(docid).subList((pagenum-1)*5,pagenum*5-1);
            }catch (Exception e2){
                try{
                    list = docCommentRepository.findByDocid(docid).subList((pagenum-1)*5,pagenum*5-2);
                }catch (Exception e3){
                    try{
                        list = docCommentRepository.findByDocid(docid).subList((pagenum-1)*5,pagenum*5-3);
                    }catch (Exception e4){
                        try {
                            list = docCommentRepository.findByDocid(docid).subList((pagenum-1)*5,pagenum*5-4);
                        }catch (Exception e5) {
                            return null;
                        }
                    }
                }
            }
        }
        List<DocRUser> docRelays = new ArrayList<>();
        for (DocComment docComment : list){
            List<DocRUser> result = new ArrayList<>();
            docRelays.clear();
            List<DocRelay> docRelay = docRelayRepository.findByCommentidAndRcommentidEquals(docComment.getId(),0);
            for (int i = 0;i < docRelay.size();i++)
                docRelays.add(new DocRUser(docRelay.get(i),userRepository.findOne(docRelay.get(i).getUserid())));
            for(DocRUser docRUser : docRelays){
                result.add(docRUser);
                findDSonRelay(result,docComment.getId(),docRUser.getDocRelay().getId());
            }
            if (result.size() == 0){
                map.put(new DocCUser(docComment,userRepository.findOne(docComment.getUserid())),null);
            }else{
                map.put(new DocCUser(docComment,userRepository.findOne(docComment.getUserid())),result);
            }
        }
        return map;
    }

    public void findDSonRelay(List<DocRUser> result,long cid,long rid){   //递归查找文章子评论
        try{
            for(DocRelay docRelay : docRelayRepository.findByCommentidAndRcommentid(cid,rid)){
                result.add(new DocRUser(docRelay,userRepository.findOne(docRelay.getUserid())));
                findDSonRelay(result,cid,docRelay.getId());
            }
        }catch (Exception e){
            return;
        }
    }

    @Override
    public Map<ForumCUser, List<ForumRUser>> getForumComment(long docid, int pagenum) {
        LinkedHashMap<ForumCUser,List<ForumRUser>> map = new LinkedHashMap<>();
        List<ForumComment> list = null;
        try{
            list = forumCommentRepository.findByForumid(docid).subList((pagenum-1)*5,pagenum*5);
        }catch (Exception e){
            try {
                list = forumCommentRepository.findByForumid(docid).subList((pagenum-1)*5,pagenum*5-1);
            }catch (Exception e2){
                try{
                    list = forumCommentRepository.findByForumid(docid).subList((pagenum-1)*5,pagenum*5-2);
                }catch (Exception e3){
                    try{
                        list = forumCommentRepository.findByForumid(docid).subList((pagenum-1)*5,pagenum*5-3);
                    }catch (Exception e4){
                        try {
                            list = forumCommentRepository.findByForumid(docid).subList((pagenum-1)*5,pagenum*5-4);
                        }catch (Exception e5) {
                            return null;
                        }
                    }
                }

            }
        }
        List<ForumRUser> forumRelays = new ArrayList<>();
        for (ForumComment forumComment : list){
            List<ForumRUser> result = new ArrayList<>();
            forumRelays.clear();
            List<ForumRelay> forumRelay = forumRelayRepository.findByCommentidAndRcommentidEquals(forumComment.getId(),0);
            for(int i = 0;i < forumRelay.size();i ++)
                forumRelays.add(new ForumRUser(forumRelay.get(i),userRepository.findOne(forumRelay.get(i).getUserid())));
            for(ForumRUser forumRUser : forumRelays){
                result.add(forumRUser);
                findFSonRelay(result,forumComment.getId(),forumRUser.getForumRelay().getId());
            }
            if (result.size() == 0){
                map.put(new ForumCUser(forumComment,userRepository.findOne(forumComment.getUserid())),null);
            }else{
                map.put(new ForumCUser(forumComment,userRepository.findOne(forumComment.getUserid())),result);
            }
        }
        return map;
    }

    public void findFSonRelay(List<ForumRUser> result,long cid,long rid){
        try{
            for(ForumRelay forumRelay : forumRelayRepository.findByCommentidAndRcommentid(cid,rid)){
                result.add(new ForumRUser(forumRelay,userRepository.findOne(forumRelay.getUserid())));
                findFSonRelay(result,cid,forumRelay.getId());
            }
        }catch (Exception e){
            return;
        }
    }

    @Override
    public Map<VideoCUser, List<VideoRUser>> getVideoComment(long docid,int pagenum) {
        LinkedHashMap<VideoCUser,List<VideoRUser>> map = new LinkedHashMap<>();
        List<VideoComment> list = null;
        try{
            list = videoCommentRepository.findByVideoid(docid).subList((pagenum-1)*5,pagenum*5);
        }catch (Exception e){
            try {
                list = videoCommentRepository.findByVideoid(docid).subList((pagenum-1)*5,pagenum*5-1);
            }catch (Exception e2){
                try{
                    list = videoCommentRepository.findByVideoid(docid).subList((pagenum-1)*5,pagenum*5-2);
                }catch (Exception e3){
                    try{
                        list = videoCommentRepository.findByVideoid(docid).subList((pagenum-1)*5,pagenum*5-3);
                    }catch (Exception e4){
                        try {
                            list = videoCommentRepository.findByVideoid(docid).subList((pagenum-1)*5,pagenum*5-4);
                        }catch (Exception e5) {
                            return null;
                        }
                    }
                }

            }
        }
        List<VideoRUser> videoRelays = new ArrayList<>();
        for (VideoComment videoComment : list){
            List<VideoRUser> result = new ArrayList<>();
            videoRelays.clear();
            List<VideoRelay> videoRelay = videoRelayRepository.findByCommentidAndRcommentidEquals(videoComment.getId(),0);
            for (int i = 0;i < videoRelay.size();i ++)
                videoRelays.add(new VideoRUser(videoRelay.get(i),userRepository.findOne(videoRelay.get(i).getUserid())));
            for(VideoRUser videoRUser : videoRelays){
                result.add(videoRUser);
                findVSonRelay(result,videoComment.getId(),videoRUser.getVideoRelay().getId());
            }
            if (result.size() == 0){
                map.put(new VideoCUser(videoComment,userRepository.findOne(videoComment.getUserid())),null);
            }else{
                map.put(new VideoCUser(videoComment,userRepository.findOne(videoComment.getUserid())),result);
            }
        }
        return map;
    }

    public void findVSonRelay(List<VideoRUser> result, long cid, long rid){
        try{
            for(VideoRelay videoRelay : videoRelayRepository.findByCommentidAndRcommentid(cid,rid)){
                result.add(new VideoRUser(videoRelay,userRepository.findOne(videoRelay.getUserid())));
                findVSonRelay(result,cid,videoRelay.getId());
            }
        }catch (Exception e){
            return;
        }
    }

    //进行输入的心情中处理文本除需要的img html元素以外的删除。
    public String deleteHTML(String comment) {
        comment.replaceAll("<", "&lt");
        comment.replaceAll(">", "&gt");
        comment.replaceAll("&", "&amp");
        comment.replaceAll("\"", "&quot");
        comment.replaceAll("'", "&apos");
        return comment;
    }
}
