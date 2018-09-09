package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.returnMessage.BackAllMessage;
import com.example.multimedia.repository.DocumentRepository;
import com.example.multimedia.repository.UserRepository;
import com.example.multimedia.service.BackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BackServiceImpl implements BackService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DocumentRepository documentRepository;


    //取得后台首页的数据
    @Override
    public BackAllMessage getAllMsg(){
        //根据用户性别数据统计
        List<Long> mannum = new ArrayList<>();
        mannum.add(userRepository.countAllBySex(1));
        mannum.add(userRepository.countAllBySex(2));
        mannum.add(userRepository.countAllBySex(0));

        //根据文章类型统计
        List<Long> dtypenum = new ArrayList<>();
        dtypenum.add(documentRepository.countAllByKindEquals("internet"));
        dtypenum.add(documentRepository.countAllByKindEquals("law"));
        dtypenum.add(documentRepository.countAllByKindEquals("medicine"));
        dtypenum.add(documentRepository.countAllByKindEquals("economy"));
        dtypenum.add(documentRepository.countAllByKindEquals("history"));
        dtypenum.add(documentRepository.countAllByKindEquals("science"));
        dtypenum.add(documentRepository.countAllByKindEquals("art"));

        return new BackAllMessage();
    }
}
