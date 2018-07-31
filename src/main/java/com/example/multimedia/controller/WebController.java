package com.example.multimedia.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @GetMapping({"/","/html/index.html"})
    public String index(){
        return "html/index";
    }

    @GetMapping("/html/article.html")
    public String article(){
        return "html/article";
    }

    @GetMapping("/html/map.html")
    public String map(){
        return "html/map";
    }

    @GetMapping("/html/personalCenter.html")
    public String personalCenter(){
        try{
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            String username = userDetails.getUsername();
            return "html/personalCenter";
        }catch (Exception e){
            return "html/index";
        }

    }

    @GetMapping("/html/RichEditor.html")
    public String richEditor(){
        return "html/RichEditor";
    }

    @GetMapping("/html/search.html")
    public String search(){
        return "html/search";
    }

    @GetMapping("/html/UI.html")
    public String ui(){
        return "html/UI";
    }
}
