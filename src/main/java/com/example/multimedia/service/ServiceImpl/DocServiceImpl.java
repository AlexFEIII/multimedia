package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.DocRecycler;
import com.example.multimedia.domain.Document;
import com.example.multimedia.domain.MulUser;
import com.example.multimedia.domain.Recycler;
import com.example.multimedia.domain.returnMessage.DocType;
import com.example.multimedia.domain.returnMessage.DocUserView;
import com.example.multimedia.domain.returnMessage.GetDoc;
import com.example.multimedia.repository.*;
import com.example.multimedia.service.DocService;
import com.example.multimedia.service.HistoryService;
import com.example.multimedia.service.UserService;
import org.apache.http.HttpResponse;
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

import java.util.*;

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
    private DocUpvoteRepository docUpvoteRepository;
    @Autowired
    private CollectDocRepository collectDocRepository;
    @Autowired
    private CollectUserRepository collectUserRepository;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private CollectDKindRepository collectDKindRepository;


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

    //获取一篇文章的点赞、关注、收藏情况
    @Override
    public List<Boolean> getBoolean(long docid) {
        boolean isfollow = false,isupvote = false,iscollect = false;
        try{
            MulUser mulUser = userRepository.findByUsername(userService.getUsername());
            if (docUpvoteRepository.findByDocidAndAndUserid(docid,mulUser.getId()) != null){
                isupvote = true;
            }
            if (collectDocRepository.findByUseridAndDocid(mulUser.getId(),docid) != null){
                iscollect = true;
            }
            if (collectUserRepository.findByUseridAndCuserid(mulUser.getId(),documentRepository.findOne(docid).getUserid()) != null){
                isfollow = true;
            }

        }catch (Exception e){
            //ignore
        }
        return Arrays.asList(isfollow,isupvote,iscollect);
    }

    //得到一篇文章
    @Override
    public GetDoc getOneDoc(long id){
        Document document = documentRepository.findOne(id);
        MulUser userinfor = userRepository.findOne(document.getUserid());
        try{
            System.out.println("调用增加历史方法！");
            historyService.dhistory(id);
        }catch (Exception e){}
        return new GetDoc(document,userinfor);
    }

    //得到我的文章
    @Override
    public List<DocUserView> getMineDoc(String type) {
        List<DocUserView> docUserViews = new ArrayList<>();
        MulUser mulUser = userRepository.findByUsername(userService.getUsername());
        List<Document> documents = documentRepository.findByUseridAndKindOrderByDateAsc(mulUser.getId(),type);
        for (Document document : documents){
            docUserViews.add(new DocUserView(document,mulUser));
        }
        return docUserViews;
    }

    //得到别人的文章
    @Override
    public List<DocUserView> getOthersDoc(long id, String type) {
        List<DocUserView> docUserViews = new ArrayList<>();
        MulUser mulUser = userRepository.findOne(id);
        List<Document> documents = documentRepository.findByUseridAndKindOrderByDateAsc(id,type);
        for (Document document : documents){
            docUserViews.add(new DocUserView(document,mulUser));
        }
        return docUserViews;
    }

    /*
    * 增加文章
    * */
    @Override
    public String addDoc(String title, String summary, String content, MultipartFile image, String type) {
        if (!(type.equals("internet") || type.equals("law") || type.equals("")))
        if (!title.equals(sensitivewordFilter.turnWord(title))){
            return "T_SENSITIVE";
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
        Document document = new Document(title,commentService.deleteHTML(summary),commentService.deleteHTML(content),pinyin.getStringPinYin(title),mulUser.getId(),type);
        if (image != null){
            String flag = userService.uploadImage(image);
            if (flag.equals("IMAGE_N") || flag.equals("BIG") || flag.equals("WRONG_TYPE")){
                return flag;
            }
            document.setImage(flag);
        }
        documentRepository.save(document);
        return "Y";
    }

    /*
    * 修改文章
    * */
    @Override
    public String changeDoc(long documentid,String title, String summary, String content, MultipartFile image, String type) {
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
            if (type!=null) document.setKind(type);
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
            DocRecycler docRecycler = new DocRecycler(document.getTitle(),document.getSummary(),document.getContent(),document.getTpinyin(),document.getUserid(),document.getUpvotenum(),document.getCommentnum(),document.getSawnum(),document.getKind(),document.getDate());
            if (document.getImage()!=null) docRecycler.setImage(document.getImage());
            docRecyclerRepository.save(docRecycler);
            return "Y";
        }
        return "N";
    }

    //检测权限
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

    //获取某一类型的文章
    @Override
    public DocType getDocType(String type,int pagenum) {
        List<DocUserView> docUserViews = new ArrayList<>();
        Pageable pageable = new PageRequest(pagenum-1,20,new Sort(Sort.Direction.DESC,"id"));
        Page<Document> list = documentRepository.findAllByKindEquals(type,pageable);
        for (Document document:list){
            docUserViews.add(new DocUserView(document,userRepository.findOne(document.getUserid())));
        }
        boolean isCol = false;
        try{
            MulUser mulUser = userRepository.findByUsername(userService.getUsername());
            if (collectDKindRepository.findByUseridAndKindEquals(mulUser.getId(),type) != null){
                isCol = true;
            }
        }catch (Exception e){
            //ignore
        }
        return new DocType(collectDKindRepository.countAllByKindEquals(type),list.getTotalElements(),list.getTotalPages(),isCol,docUserViews);
    }
}
