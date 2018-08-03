package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.DocRecycler;
import com.example.multimedia.domain.Document;
import com.example.multimedia.domain.MulUser;
import com.example.multimedia.domain.Recycler;
import com.example.multimedia.domain.returnMessage.DocUserView;
import com.example.multimedia.repository.DocRecyclerRepository;
import com.example.multimedia.repository.DocumentRepository;
import com.example.multimedia.repository.RecyclerRepository;
import com.example.multimedia.repository.UserRepository;
import com.example.multimedia.service.DocService;
import com.example.multimedia.service.UserService;
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

@Service
public class DocServiceImpl implements DocService {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RecyclerRepository recyclerRepository;

    @Autowired
    private DocRecyclerRepository docRecyclerRepository;

    private CommentServiceImpl commentService;

    @Autowired
    private BaiduService baiduService;

    SensitivewordFilter sensitivewordFilter = new SensitivewordFilter();

    /*
    * 返回所有文章
    * */
    @Override
    public List<DocUserView> getAllDoc(int pageNum, int size, Sort.Direction direction, String key) {
        if (!(key.equals("id") || key.equals("title") || key.equals("sawnum")))
            key = "sawnum";
        Pageable pageable = new PageRequest(pageNum,size,direction,key);
        Page<Document> page = documentRepository.findAll(pageable);
        List<DocUserView> docUserViews = new ArrayList<>();
        for (Document doc : page)
            docUserViews.add(new DocUserView(doc,userRepository.findOne(doc.getUserid())));
        return docUserViews;
    }

    //得到一篇文章
    @Override
    public DocUserView getOneDoc(long id){
        Document document = documentRepository.findOne(id);
        MulUser mulUser = userRepository.findOne(document.getUserid());
        return new DocUserView(document,mulUser);
    }

    @Override
    public List<DocUserView> getMineDoc() {
        List<DocUserView> docUserViews = new ArrayList<>();
        User userDetails = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MulUser mulUser = userRepository.findByUsername(userDetails.getUsername());
        List<Document> documents = documentRepository.findByUserid(mulUser.getId());
        for (Document document : documents){
            docUserViews.add(new DocUserView(document,userRepository.findOne(document.getUserid())));
        }
        return docUserViews;
    }

    /*
    * 增加文章
    * */
    @Override
    public String addDoc(String title, String summary, String content, MultipartFile image, List<String> type) {
        if (!title.equals(sensitivewordFilter.turnWord(title))){
            return "T_SENSITIVE";
        }
        User userDetails = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        Document document = new Document(title,commentService.deleteHTML(summary),commentService.deleteHTML(content),pinyin.getStringPinYin(title),mulUser.getId());
        if (image != null){
            String flag = userService.uploadImage(image);
            if (flag.equals("IMAGE_N") || flag.equals("BIG") || flag.equals("WRONG_TYPE")){
                return flag;
            }
            document.setImage(flag);
        }
        for(String t : type){
            if (t.equals("internet")) document.setInternet(true);
            else if (t.equals("law")) document.setLaw(true);
            else if (t.equals("medicine")) document.setMedicine(true);
            else if (t.equals("economy")) document.setEconomy(true);
            else if (t.equals("history")) document.setHistory(true);
            else if (t.equals("science")) document.setScience(true);
            else document.setArt(true);
        }
        documentRepository.save(document);
        return "Y";
    }

    /*
    * 修改文章
    * */
    @Override
    public String changeDoc(long documentid,String title, String summary, String content, MultipartFile image, List<String> type) {
        //权限，如果是管理员或者文章是自己的
        Document document = documentRepository.findOne(documentid);
        if (power(documentid,document)) {
            if (title != null) {
                if (!title.equals(sensitivewordFilter.turnWord(title))) return "T_SENSITIVE";
                document.setTitle(commentService.deleteHTML(title));
                document.setTpinyin(commentService.deleteHTML(new Pinyin().getStringPinYin(title)));
            }
            if (summary != null) {
                if (!summary.equals(sensitivewordFilter.turnWord(summary))) return "S_SENSITIVE";
                document.setSummary(commentService.deleteHTML(summary));
            }
            if (content != null) {
                document.setContent(commentService.deleteHTML(content));
            }
            if (image != null) {
                String flag = userService.uploadImage(image);
                if (flag.equals("IMAGE_N") || flag.equals("BIG") || flag.equals("WRONG_TYPE")) {
                    return flag;
                }
                document.setImage(flag);
            }
            if (type.size() > 0) {
                document.setInternet(false);
                document.setLaw(false);
                document.setMedicine(false);
                document.setEconomy(false);
                document.setHistory(false);
                document.setScience(false);
                document.setArt(false);
                for (String t : type) {
                    if (t.equals("internet")) document.setInternet(true);
                    else if (t.equals("law")) document.setLaw(true);
                    else if (t.equals("medicine")) document.setMedicine(true);
                    else if (t.equals("economy")) document.setEconomy(true);
                    else if (t.equals("history")) document.setHistory(true);
                    else if (t.equals("science")) document.setScience(true);
                    else document.setArt(true);
                }
            }
            documentRepository.save(document);
            return "Y";
        }
        return "N";
    }

    /*
    * 删除文章
    * 如果是管理员可以删除
    * 如果文章是自己的可以删除
    * */
    @Override
    public String deleteDoc(long id) {
        Document document = documentRepository.findOne(id);
        if (power(id,document)){
            documentRepository.delete(id);
            recyclerRepository.save(new Recycler("doc",id));
            DocRecycler docRecycler = new DocRecycler(document.getTitle(),document.getSummary(),document.getContent(),document.getTpinyin(),document.getUserid(),document.getUpvotenum(),document.getCommentnum(),document.getSawnum(),document.getDate());

            if (document.getImage()!=null) docRecycler.setImage(document.getImage());

            if (document.isInternet()) docRecycler.setInternet(true);
            else if (document.isLaw()) docRecycler.setLaw(true);
            else if (document.isMedicine()) docRecycler.setMedicine(true);
            else if (document.isEconomy()) docRecycler.setEconomy(true);
            else if (document.isHistory()) docRecycler.setHistory(true);
            else if (document.isScience()) docRecycler.setScience(true);
            else if (document.isArt()) docRecycler.setArt(true);
            docRecyclerRepository.save(docRecycler);
            return "Y";
        }
        return "N";
    }

    public boolean power(long id,Document document){
        User userDetails = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        MulUser mulUser = userRepository.findByUsername(username);
        if (userRepository.findOne(documentRepository.findOne(id).getUserid()).getUsername().equals(username) ||
                (mulUser.getRole().equals("ROLE_MANAGER") && mulUser.getPower().contains("d"))    ||
                mulUser.getRole().equals("ROLE_SMANAGER"))
            return true;
        else return false;
    }
}
