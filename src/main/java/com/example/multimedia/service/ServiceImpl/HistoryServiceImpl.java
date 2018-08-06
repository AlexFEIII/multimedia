package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.DocHistory;
import com.example.multimedia.domain.MulUser;
import com.example.multimedia.domain.SearchHistory;
import com.example.multimedia.repository.*;
import com.example.multimedia.service.HistoryService;
import com.example.multimedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    //增加文章历史
    @Override
    public void dhistory(long docid) {
        try{
            MulUser mulUser = userRepository.findByUsername(userService.getUsername());
            docHistoryRepository.save(new DocHistory(mulUser.getId(),docid));
        }catch (Exception e){
            //ignore
        }
    }

    //删除文章历史
    @Override
    public void ddoc(){
        docHistoryRepository.delete(docHistoryRepository.findByUseridAndDateBefore(userRepository.findByUsername(userService.getUsername()).getId(),new Date()));
    }

    //增加问答历史
    @Override
    public void fhistory(long forumid) {
        try{
            MulUser mulUser = userRepository.findByUsername(userService.getUsername());
            docHistoryRepository.save(new DocHistory(mulUser.getId(),forumid));
        }catch (Exception e){
            //ignore
        }
    }

    //删除问答历史
    @Override
    public void dforum(){
        forumHistoryRepository.delete(forumHistoryRepository.findByUseridAndDateBefore(userRepository.findByUsername(userService.getUsername()).getId(),new Date()));
    }

    //增加视频历史
    @Override
    public void vhistory(long videoid) {
        try{
            MulUser mulUser = userRepository.findByUsername(userService.getUsername());
            docHistoryRepository.save(new DocHistory(mulUser.getId(),videoid));
        }catch (Exception e){
            //ignore
        }
    }

    //删除视频历史
    @Override
    public void dvideo(){
        videoHistoryRepository.delete(videoHistoryRepository.findByUseridAndDateBefore(userRepository.findByUsername(userService.getUsername()).getId(),new Date()));
    }

    //增加搜索历史
    @Override
    public void shistory(String key) {
        try{
            MulUser mulUser = userRepository.findByUsername(userService.getUsername());
            searchHistoryRepository.save(new SearchHistory(mulUser.getId(),key));
        }catch (Exception e){
            //ignore
        }
    }

    //删除搜索历史
    @Override
    public void dsearch(){
        searchHistoryRepository.delete(searchHistoryRepository.findByUseridAndDateBefore(userRepository.findByUsername(userService.getUsername()).getId(),new Date()));
    }
}
