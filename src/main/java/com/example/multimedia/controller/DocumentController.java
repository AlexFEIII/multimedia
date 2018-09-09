package com.example.multimedia.controller;

import com.example.multimedia.domain.returnMessage.DocType;
import com.example.multimedia.domain.returnMessage.DocUserView;
import com.example.multimedia.domain.returnMessage.GetDoc;
import com.example.multimedia.repository.CollectDKindRepository;
import com.example.multimedia.repository.DocumentRepository;
import com.example.multimedia.repository.UserRepository;
import com.example.multimedia.service.DocService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @Autowired
    private CollectDKindRepository collectDKindRepository;


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

    @GetMapping(value = "/is",params = "docid")
    public List<Boolean> isBoolean(long docid){
        return docService.getBoolean(docid);
    }

    /**
     * 获取单篇文章
     * @param id
     * @return
     */
    @GetMapping(params = "id")
    public GetDoc getOneDoc(long id){
        return docService.getOneDoc(id);
    }

    /**
     * 获取我的 某一类型的文章
     * @param type
     * @return
     */
    @GetMapping("/mine/{type}")
    public List<DocUserView> getMineDoc(@PathVariable String type){return docService.getMineDoc(type);}

    /**
     * 获得别人的文章
     * @param type
     * @param id
     * @return
     */
    @GetMapping(value = "others/{type}",params = "id")
    public List<DocUserView> getOthersDoc(@PathVariable String type, long id){
        return docService.getOthersDoc(id,type);
    }

    /*
    * 页面加载时增加文章
    * */
    @PostMapping("/addDoc")
    public String addDoc(HttpServletResponse response){
        return docService.addDoc();
    }

    @PostMapping(value = "addProDoc",params = {"type","proid"})
    public String addProDoc(String type,long proid,HttpServletResponse response){
        return docService.addProDoc(type,proid);
    }

    /*
    * 修改图片
    * */
    @PostMapping("/change")
    public String changeDoc(@RequestParam long documentid,
                            @RequestParam MultipartFile image){
        return docService.changeDoc(documentid,image);
    }

    /**
     * 修改文章内容
     * @param id
     * @param content
     * @return
     */
    @PostMapping(value = "/changeContent",params = "id")
    public String changeContent(long id,@RequestParam String content){
        return docService.changeContent(id,content);
    }

    /**
     * 修改标题
     * @param docid
     * @param title
     * @return
     */
    @PutMapping(value = "/changeTitle",params = {"docid","title"})
    public String changeTitle(long docid,String title){
        return docService.changeTitle(docid,title);
    }

    /**
     * 修改文章类型
     * @param docid
     * @param type
     * @return
     */
    @PutMapping(value = "changeType",params = {"docid","type"})
    public String changeType(long docid,String type){
        return docService.changeType(docid,type);
    }

    /*
    * 删除文章
    * */
//    @PreAuthorize(value = "hasrole={ROLE_MANAGER,ROLE_SMANAGER} ")
    @DeleteMapping(value = "/delete",params = "id")
    public String deleteDoc(long id){
        return docService.deleteDoc(id);
    }

    @GetMapping("typemsg")
    public long[] getTypeMsg(){
        long[] ints = new long[]{documentRepository.countAllByKindEquals("internet"),
                                collectDKindRepository.countAllByKindEquals("internet"),
                                documentRepository.countAllByKindEquals("law"),
                                collectDKindRepository.countAllByKindEquals("law"),
                                documentRepository.countAllByKindEquals("medicine"),
                                collectDKindRepository.countAllByKindEquals("medicine"),
                                documentRepository.countAllByKindEquals("economy"),
                                collectDKindRepository.countAllByKindEquals("economy"),
                                documentRepository.countAllByKindEquals("history"),
                                collectDKindRepository.countAllByKindEquals("history"),
                                documentRepository.countAllByKindEquals("science"),
                                collectDKindRepository.countAllByKindEquals("science"),
                                documentRepository.countAllByKindEquals("art"),
                                collectDKindRepository.countAllByKindEquals("art")};
        return ints;
    }

    /**
     * 获取某一类型 的文章
     * @param type
     * @return
     */
    @GetMapping(params = {"type","pagenum"})
    public DocType getDocType(String type,int pagenum){
        return docService.getDocType(type,pagenum);
    }
}
