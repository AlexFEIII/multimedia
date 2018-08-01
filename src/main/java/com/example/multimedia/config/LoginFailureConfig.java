package com.example.multimedia.config;

import com.alibaba.fastjson.JSON;
import com.example.multimedia.domain.LoginMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailureConfig extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        LoginMessage loginMessage = new LoginMessage();
        if (exception.getMessage().equals("NoUser")){
            loginMessage.setMsg("NoUser");
            loginMessage.setStatus("404");
        }else if(exception.getMessage().equals("NoEmail")) {
            loginMessage.setMsg("NoEmail");
            loginMessage.setStatus("404");
        }else{
            loginMessage.setMsg("UnKnown");
            loginMessage.setStatus("404");
        }
        String msg = JSON.toJSONString(loginMessage);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(msg);
        response.getWriter().flush();
    }
}
