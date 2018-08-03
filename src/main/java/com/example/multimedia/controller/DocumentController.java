package com.example.multimedia.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.multimedia.domain.Document;
import com.example.multimedia.domain.MulUser;
import com.example.multimedia.domain.returnMessage.DocUserView;
import com.example.multimedia.repository.DocumentRepository;
import com.example.multimedia.repository.UserRepository;
import com.example.multimedia.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocumentController {
    @Autowired
    private DocService docService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DocumentRepository documentRepository;


    /*
    * 获得所有文章
    * 需要参数：页码，个数，排序方式，排序依靠的关键字
    * */
    @PostMapping("/getAll")
    public List<DocUserView> getAllDoc(@RequestParam int pageNum,
                                    @RequestParam int size,
                                    @RequestParam String sort,
                                    @RequestParam String key){
        Sort.Direction direction = null;
        if (sort.equals("asc"))
            direction = Sort.Direction.ASC;
        else
            direction = Sort.Direction.DESC;
        return docService.getAllDoc(pageNum,size,direction,key);
    }

    /**
     * 获取单篇文章
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public DocUserView getOneDoc(@PathVariable long id){
        return docService.getOneDoc(id);
    }

    @GetMapping("/mine")
    public List<DocUserView> getMineDoc(){return docService.getMineDoc();}

    /*
    * 增加文章
    * 需要参数：文章标题，文章概要（可空），文章内容，文章图片（可空），类别
    * */
    @PostMapping("/add")
    public String addDoc(@RequestParam String title,
                         @RequestParam(value = "summary",required = false) String summary,
                         @RequestParam String content,
                         @RequestParam(value = "image",required = false) MultipartFile image,
                         @RequestParam List<String> type){
        return docService.addDoc(title,summary,content,image,type);
    }

    /*
    * 修改文章，可修改标题，概要，内容，图片，类别
    * */
    @PostMapping("/change")
    public String changeDoc(@RequestParam long documentid,
                            @RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "summary",required = false) String summary,
                            @RequestParam(value = "content",required = false) String content,
                            @RequestParam(value = "image",required = false) MultipartFile image,
                            @RequestParam(value = "type",required = false) List<String> type){
        return docService.changeDoc(documentid,title,summary,content,image,type);
    }

    /*
    * 删除文章
    * */
//    @PreAuthorize(value = "hasrole={ROLE_MANAGER,ROLE_SMANAGER} ")
    @DeleteMapping("/delete/{id}")
    public String deleteDoc(@PathVariable long id){
        return docService.deleteDoc(id);
    }
}
