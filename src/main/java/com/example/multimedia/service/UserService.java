package com.example.multimedia.service;

import com.example.multimedia.domain.MulUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    /*
    * 上传头像
    * */
    String uploadImage(MultipartFile file);

    /*
    * 注册
    * */
    String register(String username,String password,MultipartFile headimage);

    /*
    * 修改用户信息
    * */
    String changeUser(String password,MultipartFile headimage);

    /*
    * 返回全部用户
    * */
    Page<MulUser> getAllUser(int pageNum, int size, Sort.Direction direction, String key);

    /*
    * 设置用户角色和权限
    * */
    String setRolePower(long userid,String role,String power);
}
