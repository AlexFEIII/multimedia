package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.DocHistory;
import com.example.multimedia.domain.MulUser;
import com.example.multimedia.repository.*;
import com.example.multimedia.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

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

    @Override
    public void dhistory(long docid) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MulUser mulUser = userRepository.findByUsername(user.getUsername());
        docHistoryRepository.save(new DocHistory(mulUser.getId(),docid));

    }

    @Override
    public void fhistory(long forumid) {

    }

    @Override
    public void vhistory(long videoid) {

    }

    @Override
    public void shistory(String key) {

    }
}
