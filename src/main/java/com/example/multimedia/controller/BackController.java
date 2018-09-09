package com.example.multimedia.controller;

import com.example.multimedia.domain.returnMessage.BackAllMessage;
import com.example.multimedia.service.BackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/back")
public class BackController {

    @Autowired
    private BackService backService;

    /**
     * 取得后台首页的数据
     * @return
     */
    @GetMapping("allMsg")
    public BackAllMessage getAllMsg(){
        return backService.getAllMsg();
    }
}
