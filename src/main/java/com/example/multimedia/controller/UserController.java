package com.example.multimedia.controller;

import com.example.multimedia.domain.MulUser;
import com.example.multimedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    * 注册请求
    * N:图片不合法 BIG：图片太大 WRONG_TYPE：图片格式错误
    * */
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password){
        return userService.register(username,password);
    }

    /*
    * 修改密码
    * */
    @PostMapping("/changePass")
    public String change(@RequestParam("password") String password){
        return userService.changeUser(password,null);
    }

    /*
    * 修改头像
    * */
    @PostMapping("/changeImage")
    public String change(@RequestParam MultipartFile headimage){
        return userService.changeUser(null,headimage);
    }

    /*
    * 返回所有的用户
    * */
    @PostMapping("/getAllUser")
    public Page<MulUser> getAllUser(@RequestParam int pageNum,
                                    @RequestParam int size,
                                    @RequestParam String sort,
                                    @RequestParam String key){
        Sort.Direction direction = null;
        if (sort.equals("asc"))
            direction = Sort.Direction.ASC;
        else
            direction = Sort.Direction.DESC;
        return userService.getAllUser(pageNum,size,direction,key);
    }

    /*
    * 设置用户角色 and 权限
    * */
    @PreAuthorize("hasRole('ROLE_SMANAGER')")
    @PostMapping("/RolePower")
    public String setRolePower(@RequestParam long userid,
                               @RequestParam String role,
                               @RequestParam(value = "power",required = false) String power){
        return userService.setRolePower(userid,role,power); //power 默认文章，视频管理
    }

    /**
     * 判断是否已经登陆
     * @return
     */
    @GetMapping("isLogin")
    public MulUser isLogin(){
        return userService.isLogin();
    }
}
