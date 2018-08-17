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
        System.out.println(headimage.getName());
        return userService.changeUser(null,headimage);
    }

    /**
     * 修改用户基础信息
     * @param sex 性别,1男，2女，0保密
     * @param personality 个人简介
     * @param address 地址
     * @param qq
     * @param job 工作
     * @param weburl 个人网站
     * @return
     */
    @PostMapping("/changeUser")
    public String changeUser(@RequestParam int sex,
                             @RequestParam String personality,
                             @RequestParam String address,
                             @RequestParam String qq,
                             @RequestParam String job,
                             @RequestParam String weburl){
        return userService.changeUserInfor(sex,personality,address,qq,job,weburl);
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

    @GetMapping(params = "id")
    public MulUser getOne(long id){
        return userService.getOne(id);
    }
}
