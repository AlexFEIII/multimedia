package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.MulUser;
import com.example.multimedia.domain.Recycler;
import com.example.multimedia.domain.Video;
import com.example.multimedia.domain.VideoRecycler;
import com.example.multimedia.domain.returnMessage.VideoUser;
import com.example.multimedia.repository.RecyclerRepository;
import com.example.multimedia.repository.UserRepository;
import com.example.multimedia.repository.VideoRepository;
import com.example.multimedia.service.UserService;
import com.example.multimedia.service.VideoService;
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
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private BaiduService baiduService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private RecyclerRepository recyclerRepository;

    SensitivewordFilter sensitivewordFilter = new SensitivewordFilter();

    //获得所有视频
    @Override
    public List<VideoUser> getAllVideo(int pageNum, int size, Sort.Direction direction, String key) {
        if (!(key.equals("id") || key.equals("sawnum") || key.equals("title"))) {
            key = "sawnum";
        }
        Pageable pageable = new PageRequest(pageNum,size,direction,key);
        Page<Video> page = videoRepository.findAll(pageable);
        List<VideoUser> videoUsers = new ArrayList<>();
        for (Video video:page)
            videoUsers.add(new VideoUser(video,userRepository.findOne(video.getUserid())));
        return videoUsers;
    }

    //取得一篇视频
    @Override
    public VideoUser getOneVideo(long id) {
        Video video = videoRepository.findOne(id);
        MulUser mulUser = userRepository.findOne(video.getUserid());
        return new VideoUser(video,mulUser);
    }

    //取得我的视频
    @Override
    public List<VideoUser> getMineVideo(){
        List<VideoUser> videoUsers = new ArrayList<>();
        MulUser mulUser = userService.getUsername();
        List<Video> videos = videoRepository.findByUseridOrderByDateAsc(mulUser.getId());
        for (Video video : videos){
            videoUsers.add(new VideoUser(video,userRepository.findOne(video.getUserid())));
        }
        return videoUsers;
    }

    //取得别人的视频
    @Override
    public List<VideoUser> getOthersVideo(long userid){
        List<VideoUser> videoUsers = new ArrayList<>();
        MulUser mulUser = userRepository.findOne(userid);
        List<Video> videos = videoRepository.findByUseridOrderByDateAsc(mulUser.getId());
        for (Video video : videos){
            videoUsers.add(new VideoUser(video,userRepository.findOne(video.getUserid())));
        }
        return videoUsers;
    }

    /*
    * 增加视频
    * */
    @Override
    public String addVideo(String title, String summary, String url, MultipartFile image, String type) {
        try{
            if (!title.equals(sensitivewordFilter.turnWord(title))){
                return "T_SENSITIVE";
            }
            if (!summary.equals(sensitivewordFilter.turnWord(summary))) return "S_SENSITIVE";
            MulUser mulUser = userService.getUsername();
            Pinyin pinyin = new Pinyin();
            String flag = userService.uploadImage(image);
            if (flag.equals("IMAGE_N") || flag.equals("BIG") || flag.equals("WRONG_TYPE")){
                return flag;
            }
            Video video = new Video(commentService.deleteHTML(title),pinyin.getStringPinYin(title),flag,commentService.deleteHTML(summary),url,mulUser.getId(),type);
            videoRepository.save(video);

        }catch (ClassCastException e){
            return "ERROR";
        }
        return "Y";
    }

    /*
    * 删除视频
    * */
    @Override
    public String deleteVideo(long id) {
        Video video = videoRepository.findOne(id);
        if (power(id,video)){
            videoRepository.delete(id);
            recyclerRepository.save(new Recycler("video",id));
            VideoRecycler videoRecycler = new VideoRecycler(video.getTitle(),new Pinyin().getStringPinYin(video.getTitle()),video.getImage(),video.getSummary(),video.getUrl(),video.getKind(),video.getDate());
            return "Y";
        }
        return "N";
    }

    /*
    * 修改视频
    * */
    @Override
    public String changeVideo(long videoid,String title,MultipartFile image,String summary,String url,String type) {
        Video video = videoRepository.findOne(videoid);
        if (power(videoid,video)) {
            if (title != null) {
                if (!title.equals(sensitivewordFilter.turnWord(title))) return "T_SENSITIVE";
                title = commentService.deleteHTML(title);
                video.setTitle(title);
                video.setTpinyin(new Pinyin().getStringPinYin(title));
            }
            if (image != null) video.setImage(userService.uploadImage(image));
            if (summary != null) {
                if (!summary.equals(sensitivewordFilter.turnWord(summary))) return "S_SENSITIVE";
                video.setSummary(commentService.deleteHTML(summary));
            }
            if (url != null) video.setUrl(url);
            if (type != null) video.setKind(type);
            videoRepository.save(video);
            return "Y";
        }
        return "N";
    }

    private boolean power(long videoid,Video video){
        MulUser mulUser = userService.getUsername();
        if (userRepository.findOne(video.getUserid()).getUsername().equals(mulUser.getUsername()) ||
                (mulUser.getRole().equals("ROLE_MANAGER") && mulUser.getPower().contains("v")) ||
                mulUser.getRole().equals("ROLE_SMANAGER"))
            return true;
        else return false;
    }
}
