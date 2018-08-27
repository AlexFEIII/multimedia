package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.*;
import com.example.multimedia.domain.returnMessage.*;
import com.example.multimedia.repository.*;
import com.example.multimedia.service.CommentService;
import com.example.multimedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private DocCommentRepository docCommentRepository;
    @Autowired
    private ForumProblemRepository forumProblemRepository;
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
    @Autowired
    private UserService userService;
    @Autowired
    private DocCUpvoteRepository docCUpvoteRepository;
    @Autowired
    private ForumCUpvoteRepository forumCUpvoteRepository;
    @Autowired
    private VideoCUpvoteRepository videoCUpvoteRepository;
    @Autowired
    private CollectFCRepository collectFCRepository;
    @Autowired
    private ForumCommentRepository forumCommentRepository;

    SensitivewordFilter sensitivewordFilter = new SensitivewordFilter();

    /*
    * 评论功能
    * */
    @Override
    public Map<Long,String> comment(String type, long objid, String content) {
        if (content.equals("")) return null;
        long userid = userRepository.findByUsername(userService.getUsername()).getId();
        long ruserid = docCommentRepository.findOne(objid).getUserid();
        content = sensitivewordFilter.turnWord(content);
        long id = 0;
        if (type.equals("doc")){
            DocComment docComment = new DocComment(deleteHTML(content),objid,userid,ruserid);
            docCommentRepository.save(docComment);
            id = docComment.getId();
        }else if (type.equals("video")){
            VideoComment videoComment = new VideoComment(deleteHTML(content),objid,userid,ruserid);
            videoCommentRepository.save(videoComment);
            id = videoComment.getId();
        }else if (type.equals("docR")){
            DocRelay docRelay = new DocRelay(deleteHTML(content),objid,userid,ruserid);
            docRelayRepository.save(docRelay);
            id = docRelay.getId();
        }else if (type.equals("videoR")){
            VideoRelay videoRelay = new VideoRelay(deleteHTML(content),objid,userid,ruserid);
            videoRelayRepository.save(videoRelay);
            id = videoRelay.getId();
        }else{
            return null;
        }
        Map<Long,String> map = new HashMap<Long,String>();
        map.put(id,content);
        return map;
    }

    /*
    * 回复 回复
    * */
    @Override
    public Map<Long,String> replyR(String type,String content,long objid,long rcommentid){
        if (content.equals("")) return null;
        long userid = userRepository.findByUsername(userService.getUsername()).getId();
        content = sensitivewordFilter.turnWord(content);
        long id = 0;
        if (type.equals("docRR")){
            long ruserid = docRelayRepository.findOne(rcommentid).getUserid();
            DocRelay docRelay = new DocRelay(deleteHTML(content),objid,rcommentid,userid,ruserid);
            docRelayRepository.save(docRelay);
            id = docRelay.getId();
        }else if (type.equals("forumRR")){
            long ruserid;
            if (rcommentid != 0){
                ruserid = forumRelayRepository.findOne(rcommentid).getUserid();
            }else{
                ruserid = forumProblemRepository.findOne(objid).getUserid();
            }
            ForumRelay forumRelay = new ForumRelay(deleteHTML(content),objid,rcommentid,userid,ruserid);
            forumRelayRepository.save(forumRelay);
            id = forumRelay.getId();
        }else if (type.equals("videoRR")){
            long ruserid = videoRelayRepository.findOne(rcommentid).getUserid();
            VideoRelay videoRelay = new VideoRelay(deleteHTML(content),objid,rcommentid,userid,ruserid);
            videoRelayRepository.save(videoRelay);
            id = videoRelay.getId();
        }else{
            return null;
        }
        Map<Long,String> map = new HashMap<>();
        map.put(id,content);
        return map;
    }

    /*
    * 删除回复
    * */
    @Override
    public List<Long> deleteComment(String type,long docid,long commentid,long rcommentid) {
        List<Long> longs = new ArrayList<>();
        longs.add(rcommentid);
        try{
            User userDetails = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            if (type.equals("doc")){
                //如果文章是自己的可以删除||如果评论是自己的，可以删除||如果被回复的用户是自己，可以删除
                if (userRepository.findOne(documentRepository.findOne(docid).getUserid()).getUsername().equals(username) ||
                        userRepository.findOne(docCommentRepository.findOne(commentid).getUserid()).getUsername().equals(username) ||
                        userRepository.findOne(docRelayRepository.findOne(rcommentid).getUserid()).getUsername().equals(username)){
                    docRelayRepository.delete(rcommentid);
                    return deleteDocRelay(rcommentid,longs);
                }
            }else if (type.equals("forum")){
                if (userRepository.findOne(forumRepository.findOne(docid).getUserid()).getUsername().equals(username) ||
                        userRepository.findOne(forumProblemRepository.findOne(commentid).getUserid()).getUsername().equals(username) ||
                        userRepository.findOne(forumRelayRepository.findOne(rcommentid).getUserid()).getUsername().equals(username)){
                    forumRelayRepository.delete(rcommentid);
                    return deleteForumRelay(rcommentid,longs);
                }
            }else if(type.equals("video")){
                if (userRepository.findOne(videoRepository.findOne(docid).getUserid()).getUsername().equals(username) ||
                        userRepository.findOne(videoCommentRepository.findOne(commentid).getUserid()).getUsername().equals(username) ||
                        userRepository.findOne(videoRelayRepository.findOne(rcommentid).getUserid()).getUsername().equals(username)){
                    videoRelayRepository.delete(rcommentid);
                    return deleteVideoRelay(rcommentid,longs);
                }
            }
        }catch (Exception e){
            //ignore
        }
        return null;
    }

    private List<Long> deleteDocRelay(long rcommentid,List<Long> longs){
        List<DocRelay> docRelays = docRelayRepository.findByRcommentid(rcommentid);
        for (DocRelay docRelay:docRelays){
            deleteDocRelay(docRelay.getId(),longs);
            longs.add(docRelay.getId());
            docRelayRepository.delete(docRelay);
        }
        return longs;
    }
    private List<Long> deleteForumRelay(long rcommentid,List<Long> longs){
        List<ForumRelay> forumRelays = forumRelayRepository.findByRcommentid(rcommentid);
        for (ForumRelay forumRelay:forumRelays){
            deleteForumRelay(forumRelay.getId(),longs);
            longs.add(forumRelay.getId());
            forumRelayRepository.delete(forumRelay);
        }
        return longs;
    }
    private List<Long> deleteVideoRelay(long rcommentid,List<Long> longs){
        List<VideoRelay> videoRelays = videoRelayRepository.findByRcommentid(rcommentid);
        for (VideoRelay videoRelay:videoRelays){
            deleteVideoRelay(videoRelay.getId(),longs);
            longs.add(videoRelay.getId());
            videoRelayRepository.delete(videoRelay);
        }
        return longs;
    }

    //删除评论
    @Override
    public String deleteComment(String type,long docid, long commentid) {
        User userDetails = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        //如果文档是自己的可以删||如果评论是自己的可以删
        if (type.equals("doc")){
            //如果文章是自己的可以删除||如果评论是自己的，可以删除||如果被回复的用户是自己，可以删除
            if (userRepository.findOne(documentRepository.findOne(docid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(docCommentRepository.findOne(commentid).getUserid()).getUsername().equals(username)){
                docCommentRepository.delete(commentid);
                docCUpvoteRepository.deleteAllByCommentid(commentid);
                docRelayRepository.deleteAllByCommentid(commentid);
                return "Y";
            }
            return "N";
        }else if (type.equals("forum")){
            if (userRepository.findOne(forumRepository.findOne(docid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(forumProblemRepository.findOne(commentid).getUserid()).getUsername().equals(username)){
                forumProblemRepository.delete(commentid);
                forumCUpvoteRepository.deleteAllByCommentid(commentid);
                forumRelayRepository.deleteAllByCommentid(commentid);
                return "Y";
            }
            return "N";
        }else {
            if (userRepository.findOne(videoRepository.findOne(docid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(videoCommentRepository.findOne(commentid).getUserid()).getUsername().equals(username)){
                videoCommentRepository.delete(commentid);
                videoCUpvoteRepository.deleteAllByCommentid(commentid);
                videoRelayRepository.deleteAllByCommentid(commentid);
                return "Y";
            }
            return "N";
        }
    }

    //返回评论*3
    @Override
    public List<DCView> getDocComment(long docid, int pagenum) {
        List<DCView> dcViews = new ArrayList<>();
        boolean isLogin = true;
        MulUser mulUser = null;
        try{
            mulUser = userRepository.findByUsername(userService.getUsername());
        }catch (Exception e){
            isLogin = false;
        }
        Pageable pageable = new PageRequest(pagenum-1,12,new Sort(Sort.Direction.DESC,"id"));
        Page<DocComment> list = docCommentRepository.findByDocid(docid,pageable);
        for (DocComment docComment : list){
//            List<DocRUser> result = new ArrayList<>();
//            docRelays.clear();
//            List<DocRelay> docRelay = docRelayRepository.findByCommentidAndRcommentidEquals(docComment.getId(),0);
//            for (int i = 0;i < docRelay.size();i++)
//                docRelays.add(new DocRUser(docRelay.get(i),userRepository.findOne(docRelay.get(i).getUserid())));
//            for(DocRUser docRUser : docRelays){
//                result.add(docRUser);
//                findDSonRelay(result,docComment.getId(),docRUser.getDocRelay().getId());
//            }
//            if (result.size() == 0){
//                map.put(new DocCUser(docComment,userRepository.findOne(docComment.getUserid())),null);
//            }else{
//                map.put(new DocCUser(docComment,userRepository.findOne(docComment.getUserid())),result);
//            }
            List<DocRUser> docRelays = new ArrayList<>();
            List<DocRelay> docRelay = docRelayRepository.findByCommentid(docComment.getId());
            for (int i = 0;i < docRelay.size();i ++){
                MulUser user = userRepository.findOne(docRelay.get(i).getUserid());
                MulUser ruser = userRepository.findOne(docRelay.get(i).getReplyid());
                docRelays.add(new DocRUser(docRelay.get(i),user.getNickname(),user.getId(),ruser.getNickname(),ruser.getId()));
            }
            boolean flag = false;
            if (isLogin){
                if (docCUpvoteRepository.findByCommentidAndUserid(docComment.getId(),mulUser.getId()) != null) flag = true;
            }
            if (docRelays.size() != 0){
                dcViews.add(new DCView(list.getTotalPages(),list.getTotalElements(),flag,new DocCUser(docComment,userRepository.findOne(docComment.getUserid())),docRelays));
            }else{
                dcViews.add(new DCView(list.getTotalPages(),list.getTotalElements(),flag,new DocCUser(docComment,userRepository.findOne(docComment.getUserid())),null));
            }
        }
        return dcViews;
    }

//    public void findDSonRelay(List<DocRUser> result,long cid,long rid){   //递归查找文章子评论
//        try{
//            for(DocRelay docRelay : docRelayRepository.findByCommentidAndRcommentid(cid,rid)){
//                result.add(new DocRUser(docRelay,userRepository.findOne(docRelay.getUserid())));
//                findDSonRelay(result,cid,docRelay.getId());
//            }
//        }catch (Exception e){
//            return;
//        }
//    }

    @Override
    public List<FCView> getForumPro(long docid, int pagenum) {
        List<FCView> fcViews = new ArrayList<>();
        boolean isLogin = true;
        MulUser mulUser = null;
        try{
            mulUser = userRepository.findByUsername(userService.getUsername());
        }catch (Exception e){
            isLogin = false;
        }
        Pageable pageable = new PageRequest(pagenum-1,12,new Sort(Sort.Direction.ASC,"id"));
        Page<ForumProblem> list = forumProblemRepository.findByForumid(docid,pageable);
        for (ForumProblem forumProblem : list){
            boolean isUp = false,isFollow = false;
            List<ForumRUser> forumRelays = new ArrayList<>();
            List<ForumRelay> forumRelay = forumRelayRepository.findByCommentidOrderByIdDesc(forumProblem.getId());
            for(int i = 0;i < forumRelay.size();i ++){
                MulUser user = userRepository.findOne(forumRelay.get(i).getUserid());
                MulUser ruser = userRepository.findOne(forumRelay.get(i).getReplyid());
                forumRelays.add(new ForumRUser(forumRelay.get(i),user.getNickname(),user.getId(),ruser.getNickname(),ruser.getId()));
            }
            if (isLogin){

                if (forumCUpvoteRepository.findByCommentidAndUserid(forumProblem.getId(),mulUser.getId()) != null) isUp = true;
                if (collectFCRepository.findByCommentidAndUserid(forumProblem.getId(),mulUser.getId()) != null) isFollow = true;
            }
            if (forumRelays.size() == 0){
                fcViews.add(new FCView(list.getTotalPages(),new ForumCUser(forumProblem,userRepository.findOne(forumProblem.getUserid())),null,isUp,isFollow));
            }else{
                fcViews.add(new FCView(list.getTotalPages(),new ForumCUser(forumProblem,userRepository.findOne(forumProblem.getUserid())),forumRelays,isUp,isFollow));
            }
        }
        return fcViews;
    }

    @Override
    public List<VCView> getVideoComment(long docid,int pagenum) {
        List<VCView> vcViews = new ArrayList<>();
        Pageable pageable = new PageRequest(pagenum-1,12,new Sort(Sort.Direction.DESC,"id"));
        Page<VideoComment> list = videoCommentRepository.findByVideoid(docid,pageable);
        for (VideoComment videoComment : list){
            List<VideoRUser> videoRelays = new ArrayList<>();
            List<VideoRelay> videoRelay = videoRelayRepository.findByCommentid(videoComment.getId());
            for (int i = 0;i < videoRelay.size();i ++)
                videoRelays.add(new VideoRUser(videoRelay.get(i),userRepository.findOne(videoRelay.get(i).getUserid())));
            if (videoRelays.size() == 0){
                vcViews.add(new VCView(list.getTotalPages(),new VideoCUser(videoComment,userRepository.findOne(videoComment.getUserid())),null));
            }else{
                vcViews.add(new VCView(list.getTotalPages(),new VideoCUser(videoComment,userRepository.findOne(videoComment.getUserid())),videoRelays));
            }
        }
        return vcViews;
    }

    //返回议题 问题的回复
    @Override
    public List<ForumRUser> getForumCRelay(long proid) {
        MulUser user = userRepository.findByUsername(userService.getUsername());
        MulUser ruser = userRepository.findOne(forumProblemRepository.findOne(proid).getUserid());
        List<ForumRUser> forumRUsers = new ArrayList<>();
        List<ForumRelay> forumRelays = forumRelayRepository.findByCommentidOrderByIdDesc(proid);
        for (ForumRelay forumRelay : forumRelays){
            forumRUsers.add(new ForumRUser(forumRelay,user.getNickname(),user.getId(),ruser.getNickname(),ruser.getId()));
        }
        return forumRUsers;
    }

    //返回议题 评论及其回复
    @Override
    public List<ForumComView> getForumComment(long forumid, int pagenum) {
        List<ForumComView> list = new ArrayList<>();
        Pageable pageable = new PageRequest(pagenum-1,12,new Sort(Sort.Direction.DESC,"id"));
        Page<ForumComment> forumComments = forumCommentRepository.findByForumid(forumid,pageable);
        for(ForumComment forumComment : forumComments){
            list.add(new ForumComView(userRepository.findOne(forumComment.getUserid()).getNickname(),forumComment.getUserid(),userRepository.findOne(forumComment.getRelayid()).getNickname(),forumComment.getRelayid(),forumComment));
        }
        return list;
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
