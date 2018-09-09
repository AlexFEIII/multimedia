package com.example.multimedia.config;

import com.alibaba.fastjson.JSON;
import com.example.multimedia.domain.MulUser;
import com.example.multimedia.domain.SawCount;
import com.example.multimedia.repository.SawCountRepo;
import com.example.multimedia.repository.UserRepository;
import com.example.multimedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginSuccessConfig extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    @Autowired
    private SawCountRepo sawCountRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication)throws IOException, ServletException {
        MulUser mulUser = userService.getUser();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String date = simpleDateFormat.format(new Date());

        SawCount sawCount = sawCountRepo.findByDate(date);
        if (sawCount == null){
            sawCountRepo.save(new SawCount(date,1));
        }else{
            sawCount.setCount(sawCount.getCount()+1);
            sawCountRepo.save(sawCount);
        }

        String msg  = JSON.toJSONString(mulUser);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(msg);
        response.getWriter().flush();
    }
}
