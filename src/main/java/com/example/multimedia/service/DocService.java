package com.example.multimedia.service;

import com.example.multimedia.domain.Document;
import com.example.multimedia.domain.returnMessage.DocType;
import com.example.multimedia.domain.returnMessage.DocUserView;
import com.example.multimedia.domain.returnMessage.GetDoc;
import org.apache.http.HttpResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocService {
    /*
    * 获得所有文章
    * */
    List<DocUserView> getAllDoc(int pageNum, int size, Sort.Direction direction, String key);

    //获取一篇文章的点赞、关注、收藏情况
    public List<Boolean> getBoolean(long docid);

    //得到一篇文章
    GetDoc getOneDoc(long id);

    //得到我的文章
    List<DocUserView> getMineDoc(String type);

    //得到别人的文章
    List<DocUserView> getOthersDoc(long id, String type);

    /*
    * 增加文章
    * */
    String addDoc();
    //增加议题问题收录文章
    String addProDoc(String kind,long proid);

    /*
    * 修改文章
    * */
    String changeDoc(long documentid,MultipartFile image);

    //修改文章内容
    String changeContent(long id,String content);

    //修改文章标题
    String changeTitle(long docid,String title);

    //修改文章类型
    String changeType(long docid,String kind);

    /*
    * 删除文章
    * */
    String deleteDoc(long id);

    //获取某一类型 文章
    DocType getDocType(String type,int pagenum);

    //获取议题问题的具体内容
    DocType getForumDoc(long proid,int pagenum);
}
