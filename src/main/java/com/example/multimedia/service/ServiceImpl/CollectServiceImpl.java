package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.*;
import com.example.multimedia.repository.*;
import com.example.multimedia.service.CollectService;
import com.example.multimedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private UserService userService;

    @Autowired
    private CollectDocRepository collectDocRepository;
    @Autowired
    private CollectForumRepository collectForumRepository;
    @Autowired
    private CollectVideoRepository collectVideoRepository;
    @Autowired
    private CollectUserRepository collectUserRepository;
    @Autowired
    private CollectDKindRepository collectDKindRepository;
    @Autowired
    private CollectFProRepository collectFProRepository;

    //改变文章收藏
    @Override
    public void changeDocC(long docid) {
        MulUser mulUser = userService.getUser();
        CollectDoc collectDoc = collectDocRepository.findByUseridAndDocid(mulUser.getId(),docid);
        if (collectDoc != null){
            collectDocRepository.delete(collectDoc);
        }else{
            collectDocRepository.save(new CollectDoc(mulUser.getId(),docid));
        }
    }

    //改变文章专题收藏
    @Override
    public void changeDocK(String kind) {
        MulUser mulUser = userService.getUser();
        CollectDKind collectDKind = collectDKindRepository.findByUseridAndKindEquals(mulUser.getId(),kind);
        if (collectDKind != null){
            collectDKindRepository.delete(collectDKind);
        }else{
            collectDKindRepository.save(new CollectDKind(mulUser.getId(),kind));
        }
    }

    //改变问答收藏
    @Override
    public void changeForumC(long forumid) {
        MulUser mulUser = userService.getUser();
        CollectForum collectForum = collectForumRepository.findByUseridAndForumid(mulUser.getId(),forumid);
        if (collectForum != null){
            collectForumRepository.delete(collectForum);
        }else{
            collectForumRepository.save(new CollectForum(mulUser.getId(),forumid));
        }
    }

    //改变议题问题（评论）收藏
    @Override
    public void changeForumCC(long cid) {
        MulUser mulUser = userService.getUser();
        CollectFPro collectFPro = collectFProRepository.findByCommentidAndUserid(cid,mulUser.getId());
        if (collectFPro != null){
            collectFProRepository.delete(collectFPro);
        }else{
            collectFProRepository.save(new CollectFPro(collectFPro.getForumid(),cid,mulUser.getId()));
        }
    }

    //改变视频收藏
    @Override
    public void changeVideoC(long videoid) {
        MulUser mulUser = userService.getUser();
        CollectVideo collectVideo = collectVideoRepository.findByUseridAndVideoid(mulUser.getId(),videoid);
        if (collectVideo != null){
            collectVideoRepository.delete(collectVideo);
        }else{
            collectVideoRepository.save(new CollectVideo(mulUser.getId(),videoid));
        }
    }

    //改变用户收藏
    @Override
    public void changeUserC(long cuserid) {
        MulUser mulUser = userService.getUser();
        CollectUser collectUser = collectUserRepository.findByUseridAndCuserid(mulUser.getId(),cuserid);
        if (collectUser != null){
            collectUserRepository.delete(collectUser);
        }else{
            collectUserRepository.save(new CollectUser(mulUser.getId(),cuserid));
        }
    }
}
