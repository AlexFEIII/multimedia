package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.*;
import com.example.multimedia.domain.returnMessage.DocUserView;
import com.example.multimedia.repository.*;
import com.example.multimedia.service.HistoryService;
import com.example.multimedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private DocHistoryRepository docHistoryRepository;
    @Autowired
    private ForumHistoryRepository forumHistoryRepository;
    @Autowired
    private VideoHistoryRepository videoHistoryRepository;
    @Autowired
    private SearchHistoryRepository searchHistoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private VideoRepository videoRepository;

    //增加文章历史
    @Override
    public void dhistory(long docid) {
        MulUser mulUser = userService.getUsername();
        System.out.println(docHistoryRepository.findByUseridAndDocid(mulUser.getId(),docid));
        docHistoryRepository.delete(docHistoryRepository.findByUseridAndDocid(mulUser.getId(),docid));
        docHistoryRepository.save(new DocHistory(mulUser.getId(),docid));
    }

    //得到文章历史
    @Override
    public List<DocUserView> getDHistory(){
        ddoc();
        MulUser mulUser = userService.getUsername();
        List<DocUserView> docUserViews = new ArrayList<>();
        List<DocHistory> docHistories = docHistoryRepository.findByUseridOrderByIdDesc(mulUser.getId());
        for (DocHistory docHistory : docHistories) docUserViews.add(new DocUserView(documentRepository.findOne(docHistory.getDocid()),userRepository.findOne(docHistory.getUserid())));
        return docUserViews;
    }

    //删除文章历史
    @Override
    public void ddoc(){
        docHistoryRepository.delete(docHistoryRepository.findByUseridAndDateBefore(userRepository.findByUsername(userService.getUsername().getUsername()).getId(),new Date()));
    }

    //增加问答历史
    @Override
    public void fhistory(long forumid) {
        MulUser mulUser = userService.getUsername();
        forumHistoryRepository.delete(forumHistoryRepository.findByUseridAndForumid(mulUser.getId(),forumid));
        forumHistoryRepository.save(new ForumHistory(mulUser.getId(),forumid));
    }

    //得到问答历史
    @Override
    public List<Forum> getFHistory(){
        dforum();
        try{
            MulUser mulUser = userService.getUsername();
            List<ForumHistory> forumHistories = forumHistoryRepository.findByUseridOrderByIdDesc(mulUser.getId());
            List<Forum> forums = new ArrayList<>();
            for (ForumHistory forumHistory:forumHistories) forums.add(forumRepository.findOne(forumHistory.getForumid()));
            return forums;
        }catch (Exception e){
            return null;
        }

    }

    //删除问答历史
    @Override
    public void dforum(){
        forumHistoryRepository.delete(forumHistoryRepository.findByUseridAndDateBefore(userRepository.findByUsername(userService.getUsername().getUsername()).getId(),new Date()));
    }

    //增加视频历史
    @Override
    public void vhistory(long videoid) {
        try{
            MulUser mulUser = userService.getUsername();
            docHistoryRepository.save(new DocHistory(mulUser.getId(),videoid));
        }catch (Exception e){
            //ignore
        }
    }

    //得到视频历史
    @Override
    public List<Video> getVHistory(){
        dvideo();
        MulUser mulUser = userService.getUsername();
        List<VideoHistory> videoHistories = videoHistoryRepository.findByUserid(mulUser.getId());
        List<Video> videos = new ArrayList<>();
        for (VideoHistory videoHistory:videoHistories) videos.add(videoRepository.findOne(videoHistory.getVideoid()));
        return videos;
    }

    //删除视频历史
    @Override
    public void dvideo(){
        videoHistoryRepository.delete(videoHistoryRepository.findByUseridAndDateBefore(userRepository.findByUsername(userService.getUsername().getUsername()).getId(),new Date()));
    }

    //增加搜索历史
    @Override
    public void shistory(String key) {
        try{
            MulUser mulUser = userService.getUsername();
            try{
                searchHistoryRepository.delete(searchHistoryRepository.findByUseridAndContentEquals(mulUser.getId(),key));
            }catch (Exception e){
                //ignore
            }
            searchHistoryRepository.save(new SearchHistory(mulUser.getId(),key));
        }catch (Exception e){
            //ignore
        }
    }

    //得到搜索历史
    @Override
    public List<SearchHistory> getSHistory(){
        try{
            return searchHistoryRepository.findByUseridOrderByIdDesc(userRepository.findByUsername(userService.getUsername().getUsername()).getId());
        }catch (Exception e){
            return null;
        }
    }

    //删除搜索历史
    @Override
    public void dsearch(){
        searchHistoryRepository.delete(searchHistoryRepository.findByUseridAndDateBefore(userRepository.findByUsername(userService.getUsername().getUsername()).getId(),new Date()));
    }
}
