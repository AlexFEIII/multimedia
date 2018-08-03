package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.MulUser;
import com.example.multimedia.repository.UserRepository;
import com.example.multimedia.service.SearchService;
import com.example.multimedia.service.UserService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.elasticsearch.client.transport.TransportClient;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    BaiduService baiduService = new BaiduService();
    /*
    * 上传头像
    * */
    @Override
    public String uploadImage(MultipartFile file) {
        String result = baiduService.getImage(file);
        if (result.equals("N"))
            return "IMAGE_N";
        if(file.getSize() > 2*1024*1024)
            return "BIG";
        System.out.println("file格式："+file.getContentType());
        if(!file.getContentType().equals("image/jpg")
                && !file.getContentType().equals("image/jpeg")
                && !file.getContentType().equals("image/bmp")
                && !file.getContentType().equals("image/png")
                && !file.getContentType().equals("image/gif")){
            return "WRONG_TYPE";
        }
        File localFile = null;
        try{
            localFile = File.createTempFile("tmp",null);
            file.transferTo(localFile);
            //虚拟机关闭后删除该文件
            localFile.deleteOnExit();
        }catch (Exception e){
            e.printStackTrace();
        }
        Configuration configuration = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(configuration);
        String ACCESS_KEY = "M7uC-qZnrZ75l9uEnC1CjB7csC7gR4T3LseKGB9r";
        String SECRET_KEY = "LuwbyZvXZRtCslhaPxUwm4PFtn0mOgwsBlHOQ6ME";
        String buckename = "alexfei-home";
        String key = getImageName();
        Auth auth = Auth.create(ACCESS_KEY,SECRET_KEY);
        String upToken = auth.uploadToken(buckename);
        try{
            Response response = uploadManager.put(localFile,key,upToken);
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
        }catch (QiniuException ex) {
            Response r = ex.response;
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException e) {
            }
        }
        return "http://p4lmofhit.bkt.clouddn.com/" + key;
    }

    /*
    * 注册
    * */
    @Override
    public String register(String username,String password) {
        if(username.length() < 6 || password.length() < 6)
            return "LENGTH";
        if (userRepository.findByUsername(username) != null){
            return "REUSER";
        }else{
            userRepository.save(new MulUser(username,new Pinyin().getStringPinYin(username),passwordEncoder.encode(password),"ROLE_USER"));
        }
        return "YES";
    }

    /*
    * 修改用户信息
    * */
    @Override
    public String changeUser(String password,MultipartFile headimage){
        try{
            User userDetails = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            MulUser user = userRepository.findByUsername(userDetails.getUsername());
            if (password != null){
                user.setPassword(passwordEncoder.encode(password));
            }
            if (headimage != null){
                String flag = uploadImage(headimage);
                if (flag.equals("N") || flag.equals("BIG") || flag.equals("WRONG_TYPE")){
                    return flag;
                }
                user.setHeadimage(flag);
            }
            userRepository.save(user);
        }catch (Exception e){
            return "UnSignIn";
        }

        return "Y";
    }

    /*
    * 生成随机文件名
    * */
    public String getImageName(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss-");
        String str = simpleDateFormat.format(new Date());
        int randNum = new Random(10000).nextInt();
        return new StringBuilder(str).append(String.valueOf(randNum)).toString();
    }

    /*
    * 返回全部用户
    * */
    @Override
    public Page<MulUser> getAllUser(int pageNum, int size, Sort.Direction direction, String key){
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(key.equals("id") || key.equals("username") || key.equals("role")))
            key = "id";
        if (userRepository.findByUsername(userDetails.getUsername()).getRole().equals("ROLE_MANAGE")){
            Pageable pageable = new PageRequest(pageNum,size,direction,key);
            Page<MulUser> page = userRepository.findAll(pageable);
            return page;
        }else return null;
    }

    /*
    * 设置用户角色和权限
    * */
    @Override
    public String setRolePower(long userid, String role, String power) {
        return null;
    }

    //判断是否已经登陆
    @Override
    public MulUser isLogin(){
        try{
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userRepository.findByUsername(userDetails.getUsername());
        }catch (ClassCastException e){
            //ignore
            return null;
        }
    }
}
