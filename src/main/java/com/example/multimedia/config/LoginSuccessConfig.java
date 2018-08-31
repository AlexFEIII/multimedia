package com.example.multimedia.config;

import com.alibaba.fastjson.JSON;
import com.example.multimedia.domain.MulUser;
import com.example.multimedia.repository.UserRepository;
import com.example.multimedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

public class LoginSuccessConfig extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication)throws IOException, ServletException {
        MulUser mulUser = userService.getUsername();
        String msg  = JSON.toJSONString(mulUser);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(msg);
        response.getWriter().flush();
    }
}
