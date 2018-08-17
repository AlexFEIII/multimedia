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
    String addDoc(String title, String summary, String content, MultipartFile image, String type);

    /*
    * 修改文章
    * */
    String changeDoc(long documentid,String title, String summary, String content, MultipartFile image, String type);

    /*
    * 删除文章
    * */
    String deleteDoc(long id);

    //获取某一类型 文章
    DocType getDocType(String type,int pagenum);
}
