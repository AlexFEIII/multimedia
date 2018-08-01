package com.example.multimedia.config;

import com.alibaba.fastjson.JSON;
import com.example.multimedia.domain.MulUser;
import com.example.multimedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;

public class LoginSuccessConfig extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String user = JSON.toJSONString(userRepository.findByUsername(userDetails.getUsername()));
        try{
            response.getWriter().print(user);
            response.getWriter().flush();
        }catch (Exception e){
            //ignore;
        }
    }
}
