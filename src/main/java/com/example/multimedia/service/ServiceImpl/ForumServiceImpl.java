package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.*;
import com.example.multimedia.domain.returnMessage.FKind;
import com.example.multimedia.domain.returnMessage.ForumKNum;
import com.example.multimedia.domain.returnMessage.ForumUser;
import com.example.multimedia.repository.*;
import com.example.multimedia.service.ForumService;
import com.example.multimedia.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ForumKindRepository forumKindRepository;
    @Autowired
    private CollectForumRepository collectForumRepository;
    @Autowired
    private ForumProblemRepository forumProblemRepository;
    @Autowired
    private ForumCommentRepository forumCommentRepository;

    @Autowired
    private HistoryService historyService;

    SensitivewordFilter sensitivewordFilter = new SensitivewordFilter();
    /*
    * 返回所有论坛文章
    * */
    @Override
    public List<ForumUser> getAllForum(int pageNum, int size, Sort.Direction direction, String key) {
//        if (!(key.equals("id") || key.equals("title") || key.equals("sawnum")))
//            key = "sawnum";
//        Pageable pageable = new PageRequest(pageNum,size,direction,key);
//        Page<Forum> page = forumRepository.findAll(pageable);
//        List<ForumUser> forumUsers = new ArrayList<>();
//        for (Forum forum:page)
//            forumUsers.add(new ForumUser(forum,userRepository.findOne(forum.getUserid())));
//        return forumUsers;
        return null;
    }

    //返回一篇论坛文章
    @Override
    public ForumUser getOneForum(long id){
        Forum forum = forumRepository.findOne(id);
        MulUser mulUser = userRepository.findOne(forum.getUserid());
        boolean isfollow = false;
        try{
            MulUser user = userRepository.findByUsername(userService.getUsername());
            if (collectForumRepository.findByUseridAndForumid(user.getId(),id) != null){
                isfollow = true;
            }
            historyService.fhistory(id);
        }catch (Exception e){ }
        int colnum = collectForumRepository.countAllByForumid(id);

        return new ForumUser(forum,mulUser,isfollow,forumRepository.countAllByKindEquals(forum.getKind()),colnum);
    }

    //返回我的问答
    @Override
    public List<Forum> getMineForum() {
        List<ForumUser> forumUsers = new ArrayList<>();
        MulUser mulUser = userRepository.findByUsername(userService.getUsername());
        List<Forum> forums = forumRepository.findByUseridOrderByDateAsc(mulUser.getId());
        return forums;
    }

    //返回别人的问答
    @Override
    public List<Forum> getOthersForum(long userid){
        List<ForumUser> forumUsers = new ArrayList<>();
        MulUser mulUser = userRepository.findOne(userid);
        List<Forum> forums = forumRepository.findByUseridOrderByDateAsc(mulUser.getId());
        return forums;
    }

    /*
    * 增加文章
    * */
    @Override
    public String addForum(String title, String summary, String content, MultipartFile image, String type) {
        if (!title.equals(sensitivewordFilter.turnWord(title))){
            return "T_SENSITIVE";
        }
        ForumKind forumKind = forumKindRepository.findByKindEquals(type);
        if (forumKind == null){
            return null;
        }
        MulUser mulUser = userRepository.findByUsername(userService.getUsername());
        try{
            if (summary == null)
                summary = content.substring(0,30);
        }catch (Exception e){
            summary = content;
        }
        if (!summary.equals(sensitivewordFilter.turnWord(summary))) return "S_SENSITIVE";
        Pinyin pinyin = new Pinyin();
        title = commentService.deleteHTML(title);
        Forum forum = new Forum(title,commentService.deleteHTML(summary),commentService.deleteHTML(content),pinyin.getStringPinYin(title),mulUser.getId(),forumKind);
        if (image != null){
            String flag = userService.uploadImage(image);
            if (flag.equals("N") || flag.equals("BIG") || flag.equals("WRONG_TYPE")){
                return flag;
            }
            forum.setImage(flag);
        }
        forumRepository.save(forum);
        return "Y";
    }

    //修改文章
    @Override
    public String changeForum(long forumid, String title, String summary, String content, MultipartFile image, String type) {
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
            ForumKind  forumKind = forumKindRepository.findByKindEquals(type);
            if (forumKind != null) forum.setKind(forumKind);
            forumRepository.save(forum);
            return "Y";
        }
        return "N";
    }

    //增加议题问题（标题）
    @Override
    public String addPro(long forumid, String title) {
        //检测是否含有非法字符
        if (sensitivewordFilter.checkSensitiveWord(title,0) > 0){
            return "ILLEGAL";
        }
        MulUser user = userRepository.findByUsername(userService.getUsername());
        MulUser ruser = userRepository.findOne(forumRepository.findOne(forumid).getUserid());
        ForumProblem forumProblem = new ForumProblem(title,forumid,user.getId(),ruser.getId());
        forumProblemRepository.save(forumProblem);
        return forumProblem.getId().toString();
    }

    //增加议题评论
    @Override
    public Map<Long,String> addComment(long forumid, long rcommentid, String content) {
        System.out.println("forumid: "+forumid+"  Content: "+content+"  rcommentid: "+rcommentid);
        if (content.equals("")) return null;
        try{
            MulUser user = userRepository.findByUsername(userService.getUsername());
            MulUser ruser;
            if (rcommentid == 0){
                ruser = userRepository.findOne(forumRepository.findOne(forumid).getUserid());
            }else {
                ruser = userRepository.findOne(forumCommentRepository.findOne(rcommentid).getUserid());
            }
            String sContent = sensitivewordFilter.turnWord(content);
            ForumComment forumComment = new ForumComment(commentService.deleteHTML(sContent),user.getId(),ruser.getId(),rcommentid,forumid);
            forumCommentRepository.save(forumComment);
            Map<Long,String> map = new HashMap<>();
            map.put(forumComment.getId(),sContent);
            return map;
        }catch (Exception e){
            return null;
        }
    }

    //删除议题评论
    @Override
    public List<Long> deleteComment(long formid,long commentid) {
        List<Long> longs = new ArrayList<>();
        try{
            User userDetails = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            boolean canDelete = false;
            ForumComment forumComment = forumCommentRepository.findById(commentid);
            //如果文章是自己的可以删除||如果评论是自己的，可以删除||如果被回复的用户是自己，可以删除
            if (userRepository.findOne(forumRepository.findOne(formid).getUserid()).getUsername().equals(username) ||
                    userRepository.findOne(forumComment.getUserid()).getUsername().equals(username)){
                canDelete = true;
            }
            if (!canDelete && forumComment.getRcommentid() != 0){
                if (userRepository.findOne(forumCommentRepository.findOne(forumComment.getRcommentid()).getUserid()).getUsername().equals(username)){
                    canDelete = true;
                }
            }
            if (canDelete){
                forumCommentRepository.delete(commentid);
                longs.add(commentid);
                deleteForumRelay(commentid,longs);
                return longs;
            }
        }catch (Exception e){
            //ignore
        }
        return null;
    }
    private List<Long> deleteForumRelay(long rcommentid,List<Long> longs){
        List<ForumComment> forumComments = forumCommentRepository.findByRcommentid(rcommentid);
        for (ForumComment forumComment:forumComments){
            deleteForumRelay(forumComment.getId(),longs);
            longs.add(forumComment.getId());
            forumCommentRepository.delete(forumComment);
        }
        return longs;
    }

    //设置最佳评论
    @Override
    public String best(long forumid, long commentid) {
        Forum forum = forumRepository.findOne(forumid);
        if (userRepository.findOne(forum.getUserid()).equals(userService.getUsername())){
            forum.setResultid(commentid);
        }
        return "Y";
    }

    //删除文章
    @Override
    public String deleteForum(long id) {
        Forum forum = forumRepository.findOne(id);
        if (power(id,forum)) {
            forumRepository.delete(id);
            recyclerRepository.save(new Recycler("doc",id));
            ForumRecycler forumRecycler = new ForumRecycler(forum.getTitle(),forum.getSummary(),forum.getContent(),forum.getTpinyin(),forum.getUserid(),forum.getUpvotenum(),forum.getCommentnum(),forum.getSawnum(),forum.getKind(),forum.getDate());

            if (forum.getImage()!=null) forumRecycler.setImage(forum.getImage());
            if (forum.getResultid()!=-1) forumRecycler.setResultid(forum.getResultid());

            forumRecyclerRepository.save(forumRecycler);
            return "Y";
        }
        return "N";
    }

    //修改问答类型
    @Override
    public void addKind(String kind,MultipartFile file){
        try{
            String role = userRepository.findByUsername(userService.getUsername()).getRole();
            if (role.equals("ROLE_MANAGER") || role.equals("ROLE_SMANAGER"))

                forumKindRepository.save(new ForumKind(kind,userService.uploadImage(file)));
        }catch (Exception e){
            return;
        }
    }

    //返回某一类型的问答及其文章
    @Override
    public FKind getKind(int id,int pagenum) {
        ForumKind forumKind = forumKindRepository.findOne(id);
        Pageable pageable = new PageRequest(pagenum-1,8,new Sort(Sort.Direction.DESC,"id"));
        Page<Forum> forums = forumRepository.findByKindEquals(forumKind,pageable);
        return new FKind(forumKind,forums.getTotalPages(),forums);
    }

    //返回所有问答类型
    @Override
    public List<ForumKNum> allKind(){
        List<ForumKNum> forumKNums = new ArrayList<>();
        List<ForumKind> forumKinds = forumKindRepository.findAll();
        for (ForumKind forumKind : forumKinds){
            forumKNums.add(new ForumKNum(forumKind,forumRepository.countAllByKindEquals(forumKind)));
        }
        return forumKNums;
    }

    public boolean power(long id,Forum forum){
        String username = userService.getUsername();
        MulUser mulUser = userRepository.findByUsername(username);
        if (userRepository.findOne(forumRepository.findOne(id).getUserid()).getUsername().equals(username) ||
                (mulUser.getRole().equals("ROLE_MANAGER") && mulUser.getPower().contains("d")) ||
                mulUser.getRole().equals("ROLE_SMANAGER"))
            return true;
        else return false;
    }
}
