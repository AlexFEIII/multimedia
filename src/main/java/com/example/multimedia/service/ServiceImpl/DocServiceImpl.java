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
    private CollectFProRepository collectFProRepository;


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
            MulUser mulUser = userService.getUsername();
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
        MulUser mulUser = userService.getUsername();
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

    //增加文章
    @Override
    public String addDoc() {
        try{
            MulUser mulUser = userService.getUsername();
            Document document = new Document();
            document.setUserid(mulUser.getId());
            documentRepository.save(document);
            return document.getId().toString();
        }catch (Exception e){
        }
        return null;
    }

    //z
    @Override
    public String addProDoc(String kind,long proid) {
        try{
            Document document = new Document();
            document.setKind("forum");
            document.setForumCid(proid);
            document.setUserid(userService.getUsername().getId());
            documentRepository.save(document);
            return document.getId().toString();
        }catch (Exception e){
            return null;
        }
    }

    /*
    * 修改文章
    * */
    @Override
    public String changeDoc(long documentid, String summary, String content, String image) {
        //权限，如果是管理员或者文章是自己的
        Document document = documentRepository.findOne(documentid);
        if (power(document)) {
            if (summary != null) {
                if (!summary.equals(sensitivewordFilter.turnWord(summary))) return "S_SENSITIVE";
                document.setSummary(commentService.deleteHTML(summary));
            }
            if (content != null) {
                document.setContent(commentService.deleteHTML(content));
            }
            if (image != null) {
                String flag = userService.uploadImage(userService.base64ToMultipart(image));
                if (flag.equals("IMAGE_N") || flag.equals("BIG") || flag.equals("WRONG_TYPE")) {
                    return flag;
                }
                document.setImage(flag);
            }
            documentRepository.save(document);
            return "Y";
        }
        return "N";
    }

    //修改文章标题
    @Override
    public String changeTitle(long docid, String title) {
        Document document = documentRepository.findOne(docid);
        if (power(document)){
            if (sensitivewordFilter.checkSensitiveWord(title,0) > 0){
                return "ILLEGAL";
            }
            document.setTitle(title);
            documentRepository.save(document);
            return "Y";
        }
        return "N";
    }

    //修改文章类型
    @Override
    public String changeType(long docid,String kind){
        Document document = documentRepository.findOne(docid);
        if (power(document)){
            if (kind.equals("互联网")){
                document.setKind("internet");
            }else if (kind.equals("法律")){
                document.setKind("law");
            }else if (kind.equals("医药")){
                document.setKind("medicine");
            }else if (kind.equals("经济")){
                document.setKind("economy");
            }else if (kind.equals("历史")){
                document.setKind("history");
            }else if (kind.equals("理工")){
                document.setKind("science");
            }else {
                document.setKind("art");
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
        if (power(document) || (document.getTitle() == null || document.getKind() == null)){
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
    public boolean power(Document document){
        MulUser mulUser = userService.getUsername();
        if (document.getUserid() == mulUser.getId() ||
                (mulUser.getRole().equals("ROLE_MANAGER") && mulUser.getPower().contains("d"))    ||
                mulUser.getRole().equals("ROLE_SMANAGER"))
            return true;
        else return false;
    }

    //获取某一类型的文章
    @Override
    public DocType getDocType(String type,int pagenum) {
        List<DocUserView> docUserViews = new ArrayList<>();
        Pageable pageable = new PageRequest(pagenum-1,12,new Sort(Sort.Direction.DESC,"id"));
        Page<Document> list = documentRepository.findAllByKindEquals(type,pageable);
        for (Document document:list){
            docUserViews.add(new DocUserView(document,userRepository.findOne(document.getUserid())));
        }
        boolean isCol = false;
        try{
            MulUser mulUser = userService.getUsername();
            if (collectDKindRepository.findByUseridAndKindEquals(mulUser.getId(),type) != null){
                isCol = true;
            }
        }catch (Exception e){
            //ignore
        }
        return new DocType(collectDKindRepository.countAllByKindEquals(type),list.getTotalElements(),list.getTotalPages(),isCol,docUserViews);
    }

    //获取议题问题的内容
    public DocType getForumDoc(long proid,int pagenum) {
        List<DocUserView> docUserViews = new ArrayList<>();
        Pageable pageable = new PageRequest(pagenum-1,12,new Sort(Sort.Direction.DESC,"id"));
        Page<Document> list = documentRepository.findAllByKindEqualsAndForumCid("forum",proid,pageable);
        for (Document document:list){
            docUserViews.add(new DocUserView(document,userRepository.findOne(document.getUserid())));
        }
        return new DocType(collectFProRepository.countAllById(proid),list.getTotalElements(),list.getTotalPages(),false,docUserViews);
    }
}
