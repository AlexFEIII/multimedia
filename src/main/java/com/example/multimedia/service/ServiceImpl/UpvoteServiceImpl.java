package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.*;
import com.example.multimedia.repository.*;
import com.example.multimedia.service.UpvoteService;
import com.example.multimedia.service.UserService;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private DocCommentRepository docCommentRepository;
    @Autowired
    private ForumProblemRepository forumProblemRepository;
    /*
    * 点赞功能
    * */
    @Override
    public void upvote(String type,long objid) {
        System.out.println("type: "+type+"  objid: "+objid);
        long userid = userRepository.findByUsername(userService.getUsername()).getId();
        if (type.equals("doc")){
            Document document = documentRepository.findOne(objid);
            Docupvote docUpvote = docUpvoteRepository.findByDocidAndAndUserid(objid,userid);
            if(docUpvote == null){
                docUpvoteRepository.save(new Docupvote(userid,objid));
                document.setUpvotenum(document.getUpvotenum()+1);
            } else{
                docUpvoteRepository.delete(docUpvote);
                document.setUpvotenum(document.getUpvotenum()-1);
            }
            documentRepository.save(document);
        } else if (type.equals("forum")){
            Forum forum = forumRepository.findOne(objid);
            Forumupvote forumUpvote = forumUpvoteRepository.findByForumidAndUserid(objid,userid);
            if (forumUpvote == null){
                forumUpvoteRepository.save(new Forumupvote(userid,objid));
                forum.setUpvotenum(forum.getUpvotenum()+1);
            } else{
                forumUpvoteRepository.delete(forumUpvote);
                forum.setUpvotenum(forum.getUpvotenum()-1);
            }
            forumRepository.save(forum);
        }else if (type.equals("video")){
            Videoupvote videoUpvote = videoUpvoteRepository.findByVideoidAndUserid(objid,userid);
            if (videoUpvote == null)
                videoUpvoteRepository.save(new Videoupvote(userid,objid));
            else
                videoUpvoteRepository.delete(videoUpvote);
        }else if (type.equals("DComment")){
            DocComment docComment = docCommentRepository.findOne(objid);
            DocCUpvote docCUpvote = docCUpvoteRepository.findByCommentidAndUserid(objid,userid);
            if (docCUpvote == null){
                docCUpvoteRepository.save(new DocCUpvote(userid,objid));
                docComment.setUpvotenum(docComment.getUpvotenum()+1);
            } else{
                docCUpvoteRepository.delete(docCUpvote);
                docComment.setUpvotenum(docComment.getUpvotenum()-1);
            }
            docCommentRepository.save(docComment);
        }else if (type.equals("FComment")){
            ForumProblem forumProblem = forumProblemRepository.findOne(objid);
            ForumCUpvote forumCUpvote = forumCUpvoteRepository.findByCommentidAndUserid(objid,userid);
            if (forumCUpvote == null){
                forumCUpvoteRepository.save(new ForumCUpvote(userid,objid));
                forumProblem.setUpvotenum(forumProblem.getUpvotenum()+1);
            }
            else{
                forumCUpvoteRepository.delete(forumCUpvote);
                forumProblem.setUpvotenum(forumProblem.getUpvotenum()-1);
            }
            forumProblemRepository.save(forumProblem);
        }else if (type.equals("VComment")) {
            VideoCUpvote videoCUpvote = videoCUpvoteRepository.findByCommentidAndUserid(objid,userid);
            if (videoCUpvote == null)
                videoCUpvoteRepository.save(new VideoCUpvote(userid,objid));
            else
                videoCUpvoteRepository.delete(videoCUpvote);
        }
    }
}
