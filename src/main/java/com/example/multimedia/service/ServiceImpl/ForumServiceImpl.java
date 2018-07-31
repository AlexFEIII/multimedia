package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.Forum;
import com.example.multimedia.domain.ForumRecycler;
import com.example.multimedia.domain.MulUser;
import com.example.multimedia.domain.Recycler;
import com.example.multimedia.domain.returnMessage.ForumUser;
import com.example.multimedia.repository.ForumRecyclerRepository;
import com.example.multimedia.repository.ForumRepository;
import com.example.multimedia.repository.RecyclerRepository;
import com.example.multimedia.repository.UserRepository;
import com.example.multimedia.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RecyclerRepository recyclerRepository;
    @Autowired
    private ForumRecyclerRepository forumRecyclerRepository;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private BaiduService baiduService;

    SensitivewordFilter sensitivewordFilter = new SensitivewordFilter();
    /*
    * 返回所有论坛文章
    * */
    @Override
    public List<ForumUser> getAllForum(int pageNum, int size, Sort.Direction direction, String key) {
        if (!(key.equals("id") || key.equals("title") || key.equals("sawnum")))
            key = "sawnum";
        Pageable pageable = new PageRequest(pageNum,size,direction,key);
        Page<Forum> page = forumRepository.findAll(pageable);
        List<ForumUser> forumUsers = new ArrayList<>();
        for (Forum forum:page)
            forumUsers.add(new ForumUser(forum,userRepository.findOne(forum.getUserid())));
        return forumUsers;
    }

    //返回一篇论坛文章
    @Override
    public ForumUser getOneForum(long id){
        Forum forum = forumRepository.findOne(id);
        MulUser mulUser = userRepository.findOne(forum.getUserid());
        return new ForumUser(forum,mulUser);
    }

    /*
    * 增加文章
    * */
    @Override
    public String addForum(String title, String summary, String content, MultipartFile image, List<String> type) {
        if (!title.equals(sensitivewordFilter.turnWord(title))){
            return "T_SENSITIVE";
        }
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MulUser mulUser = userRepository.findByUsername(userDetails.getUsername());
        try{
            if (summary == null)
                summary = content.substring(0,30);
        }catch (Exception e){
            summary = content;
        }
        if (!summary.equals(sensitivewordFilter.turnWord(summary))) return "S_SENSITIVE";
        Pinyin pinyin = new Pinyin();
        title = commentService.deleteHTML(title);
        Forum forum = new Forum(title,commentService.deleteHTML(summary),commentService.deleteHTML(content),pinyin.getStringPinYin(title),mulUser.getId());
        if (image != null){
            String flag = userService.uploadImage(image);
            if (flag.equals("N") || flag.equals("BIG") || flag.equals("WRONG_TYPE")){
                return flag;
            }
            forum.setImage(flag);
        }
        for(String t : type){
            if (t.equals("internet")) forum.setInternet(true);
            else if (t.equals("law")) forum.setLaw(true);
            else if (t.equals("medicine")) forum.setMedicine(true);
            else if (t.equals("economy")) forum.setEconomy(true);
            else if (t.equals("history")) forum.setHistory(true);
            else if (t.equals("science")) forum.setScience(true);
            else forum.setArt(true);
        }
        forumRepository.save(forum);
        return "Y";
    }

    /*
    * 修改文章
    * */
    @Override
    public String changeForum(long forumid, String title, String summary, String content, MultipartFile image, List<String> type) {
        Forum forum = forumRepository.findOne(forumid);
        if (power(forumid,forum)) {
            if (!title.equals(sensitivewordFilter.turnWord(title))) {
                return "T_SENSITIVE";
            }
            title = commentService.deleteHTML(title);
            if (title != null) {
                forum.setTitle(title);
                forum.setTpinyin(new Pinyin().getStringPinYin(title));
            }
            if (summary != null) {
                if (!summary.equals(sensitivewordFilter.turnWord(summary))) return "S_SENSITIVE";
                forum.setSummary(commentService.deleteHTML(summary));
            }
            if (content != null) forum.setContent(commentService.deleteHTML(content));
            if (image != null) {
                String flag = userService.uploadImage(image);
                if (flag.equals("IMAGE_N") || flag.equals("BIG") || flag.equals("WRONG_TYPE")) {
                    return flag;
                }
                forum.setImage(flag);
            }
            if (type.size() > 0) {
                forum.setInternet(false);
                forum.setLaw(false);
                forum.setMedicine(false);
                forum.setEconomy(false);
                forum.setHistory(false);
                forum.setScience(false);
                forum.setArt(false);
                for (String t : type) {
                    if (t.equals("internet")) forum.setInternet(true);
                    else if (t.equals("law")) forum.setLaw(true);
                    else if (t.equals("medicine")) forum.setMedicine(true);
                    else if (t.equals("economy")) forum.setEconomy(true);
                    else if (t.equals("history")) forum.setHistory(true);
                    else if (t.equals("science")) forum.setScience(true);
                    else forum.setArt(true);
                }
            }
            forumRepository.save(forum);
            return "Y";
        }
        return "N";
    }

    /*
    * 设置最佳评论
    * */
    @Override
    public String best(long forumid, long commentid) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Forum forum = forumRepository.findOne(forumid);
        if (userRepository.findOne(forum.getUserid()).equals(userDetails.getUsername())){
            forum.setResultid(commentid);
        }
        return "Y";
    }

    /*
    * 删除文章
    * */
    @Override
    public String deleteForum(long id) {
        Forum forum = forumRepository.findOne(id);
        if (power(id,forum)) {
            forumRepository.delete(id);
            recyclerRepository.save(new Recycler("doc",id));
            ForumRecycler forumRecycler = new ForumRecycler(forum.getTitle(),forum.getSummary(),forum.getContent(),forum.getTpinyin(),forum.getUserid(),forum.getUpvotenum(),forum.getCommentnum(),forum.getSawnum(),forum.getDate());

            if (forum.getImage()!=null) forumRecycler.setImage(forum.getImage());
            if (forum.getResultid()!=-1) forumRecycler.setResultid(forum.getResultid());

            if (forum.isInternet()) forumRecycler.setInternet(true);
            else if (forum.isLaw()) forumRecycler.setLaw(true);
            else if (forum.isMedicine()) forumRecycler.setMedicine(true);
            else if (forum.isEconomy()) forumRecycler.setEconomy(true);
            else if (forum.isHistory()) forumRecycler.setHistory(true);
            else if (forum.isScience()) forumRecycler.setScience(true);
            else if (forum.isArt()) forumRecycler.setArt(true);
            forumRecyclerRepository.save(forumRecycler);
            return "Y";
        }
        return "N";
    }

    public boolean power(long id,Forum forum){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        MulUser mulUser = userRepository.findByUsername(username);
        if (userRepository.findOne(forumRepository.findOne(id).getUserid()).getUsername().equals(username) ||
                (mulUser.getRole().equals("ROLE_MANAGER") && mulUser.getPower().contains("d")) ||
                mulUser.getRole().equals("ROLE_SMANAGER"))
            return true;
        else return false;
    }
}
