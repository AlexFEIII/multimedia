package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.Code;
import com.example.multimedia.domain.MulUser;
import com.example.multimedia.domain.returnMessage.BASE64DecodedMultipartFile;
import com.example.multimedia.repository.CodeRepository;
import com.example.multimedia.repository.UserRepository;
import com.example.multimedia.service.UserService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.IOException;
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

    @Autowired
    private CodeRepository codeRepository;

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
    public String register(String username,String password,String nickname,String code) {
        //昵称长度要求
        if(nickname.length() < 1 || nickname.length() > 16){
            return "NICK-LENGTH";
        }
        //密码长度要求
        if (password.length() < 6 || password.length() > 16){
            return "PASS-LENGTH";
        }
        //手机是否已经注册
        if (userRepository.findByUsername(username) != null){
            return "RePhone";
        }
        //判断昵称是否已存在
        if (userRepository.findByNickname(nickname) != null){
            return "ReNick";
        }
        List<Code> codes = codeRepository.findByPhoneNumEqualsAndDateAfter(username,new Date());
        System.out.println("TheCode: "+codes + "   code: "+code + "  username: "+username);
        if (codes != null){
            for (Code TheCode : codes){
                if (TheCode.getCode().equals(code)){
                    userRepository.save(new MulUser(username,nickname,new Pinyin().getStringPinYin(nickname),passwordEncoder.encode(password),"ROLE_USER"));
                    LoginServiceImpl loginService = new LoginServiceImpl();
                    loginService.loadUserByUsername(username);
                    codeRepository.delete(codes);
                    return "SUCCESS";
                }
            }

        }
        return "NO";
    }

    /*
    * 修改密码、头像
    * */
    @Override
    public String changePassword(String pass) {
        try{
            MulUser mulUser = getUser();
            mulUser.setPassword(passwordEncoder.encode(pass));
            return "Y";
        }catch (Exception e){
            return "NoUser";
        }
    }

    @Override
    public String changeHeadimage(MultipartFile headimage){
        try{
            MulUser user = getUser();
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

    //修改用户昵称、邮箱
    @Override
    public String changeNick(String nickname, String email) {
        System.out.println("nickname : "+nickname+"  email: "+email);
        try{
            MulUser mulUser = getUser();
            if (nickname != null && !nickname.equals(mulUser.getNickname())){
                if (nickname.contains(" ")){
                    return "HasSpace";
                }
                if (userRepository.findByNickname(nickname) != null){
                    return "ReNick";
                }else {
                    mulUser.setNickname(nickname);
                }
            }
            if (email != "" && (mulUser.getEmail() == null || !mulUser.getEmail().equals(email))){
                if (userRepository.findByEmail(email) != null){
                    return "ReEmail";
                }else{
                    mulUser.setEmail(email);
                }
            }
            userRepository.save(mulUser);
            return "Y";
        }catch (Exception e){
            e.printStackTrace();
            return "NoUser";
        }
    }

    //修改用户基础信息
    @Override
    public String changeUserInfor(int sex, String personality, String address, String qq, String job, String weburl) {
        MulUser mulUser = getUser();
        mulUser.setSex(sex);
        mulUser.setPersonality(personality);
        mulUser.setAddress(address);
        mulUser.setQq(qq);
        mulUser.setJob(job);
        mulUser.setWeburl(weburl);
        userRepository.save(mulUser);
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
        if (!(key.equals("id") || key.equals("username") || key.equals("role")))
            key = "id";

        if (getUser().getRole().equals("ROLE_MANAGE")){
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
            return getUser();
        }catch (ClassCastException e){
            //ignore
            return null;
        }
    }

    @Override
    public MulUser getUser(){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getUsername().contains("@")){
            return userRepository.findByEmail(user.getUsername());
        }
        return userRepository.findByUsername(user.getUsername());
    }

    //取得一个用户
    @Override
    public MulUser getOne(long id) {
        return userRepository.findOne(id);
    }

    //验证码
    @Override
    public String getCode(String nickname, String phoneNum) {
        if (userRepository.findByUsername(phoneNum) != null){
            return "RePhone";
        }else if(userRepository.findByNickname(nickname) != null){
            return "ReName";
        }else if (nickname.contains(" ")){
            return "HasSpace";
        }else{
            DuanXinService duanXinService = new DuanXinService();
            codeRepository.delete(codeRepository.findByDateBefore(new Date()));
            String codeMsg =duanXinService.duanXin();
            if (!codeMsg.equals("ERROR")){
                codeRepository.save(new Code(phoneNum,codeMsg));
            }else {
                return "ERROR";
            }
            return "Y";
        }
    }
}
