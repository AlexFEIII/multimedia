package com.example.multimedia.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ReportController {
    /**
     *
     * @param reportid 被举报的用户id
     * @param action 举报行为
     * @param content 具体的举报描述
     * @param file 行为截图
     * @return "Y"
     */
    @PostMapping("/report")
    public String addReport(@RequestParam long reportid,
                            @RequestParam String action,
                            @RequestParam String content,
                            @RequestParam MultipartFile file){
        return null;
    }

    /**
     * @param reportid 处理的举报id
     * @param result 处理结果 //websocket传送通知，并存入数据库
     * @return
     */
    @PostMapping("/reportHandle")
    public String reportHandle(long reportid,String result){
        return null;
    }


}
