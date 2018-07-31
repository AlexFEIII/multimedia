package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.*;
import com.example.multimedia.repository.*;
import com.example.multimedia.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpvoteServiceImpl implements UpvoteService {
    @Autowired
    private DocUpvoteRepository docUpvoteRepository;
    @Autowired
    private ForumUpvoteRepository forumUpvoteRepository;
    @Autowired
    private VideoUpvoteRepository videoUpvoteRepository;
    @Autowired
    private DocCUpvoteRepository docCUpvoteRepository;
    @Autowired
    private ForumCUpvoteRepository forumCUpvoteRepository;
    @Autowired
    private VideoCUpvoteRepository videoCUpvoteRepository;
    /*
    * 点赞功能
    * */
    @Override
    public void upvote(String type, long userid, long objid) {
        if (type.equals("doc")){
            Docupvote docUpvote = docUpvoteRepository.findByDocidAndAndUserid(objid,userid);
            if(docUpvote == null)
                docUpvoteRepository.save(new Docupvote(userid,objid));
            else
                docUpvoteRepository.delete(docUpvote);
        }else if (type.equals("forum")){
            Forumupvote forumUpvote = forumUpvoteRepository.findByForumidAndUserid(objid,userid);
            if (forumUpvote == null)
                forumUpvoteRepository.save(new Forumupvote(userid,objid));
            else
                forumUpvoteRepository.delete(forumUpvote);
        }else if (type.equals("video")){
            Videoupvote videoUpvote = videoUpvoteRepository.findByVideoidAndUserid(objid,userid);
            if (videoUpvote == null)
                videoUpvoteRepository.save(new Videoupvote(userid,objid));
            else
                videoUpvoteRepository.delete(videoUpvote);
        }else if (type.equals("DCommemt")){
            DocCUpvote docCUpvote = docCUpvoteRepository.findByCommentidAndUserid(objid,userid);
            if (docCUpvote == null)
                docCUpvoteRepository.save(new DocCUpvote(userid,objid));
            else docCUpvoteRepository.delete(docCUpvote);
        }else if (type.equals("FComment")){
            ForumCUpvote forumCUpvote = forumCUpvoteRepository.findByCommentidAndUserid(objid,userid);
            if (forumCUpvote == null)
                forumCUpvoteRepository.save(new ForumCUpvote(userid,objid));
            else
                forumCUpvoteRepository.delete(forumCUpvote);
        }else {
            VideoCUpvote videoCUpvote = videoCUpvoteRepository.findByCommentidAndUserid(objid,userid);
            if (videoCUpvote == null)
                videoCUpvoteRepository.save(new VideoCUpvote(userid,objid));
            else
                videoCUpvoteRepository.delete(videoCUpvote);
        }
    }
}
