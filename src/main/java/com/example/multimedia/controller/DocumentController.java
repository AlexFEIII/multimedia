package com.example.multimedia.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.multimedia.domain.Document;
import com.example.multimedia.domain.MulUser;
import com.example.multimedia.domain.returnMessage.DocType;
import com.example.multimedia.domain.returnMessage.DocUserView;
import com.example.multimedia.domain.returnMessage.GetDoc;
import com.example.multimedia.repository.CollectDKindRepository;
import com.example.multimedia.repository.DocumentRepository;
import com.example.multimedia.repository.UserRepository;
import com.example.multimedia.service.DocService;
import org.apache.coyote.Response;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
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
    * 增加文章
    * 需要参数：文章标题，文章概要（可空），文章内容，文章图片（可空），类别
    * */
    @PostMapping("/add")
    public String addDoc(@RequestParam String title,
                         @RequestParam(value = "summary",required = false) String summary,
                         @RequestParam String content,
                         @RequestParam(value = "image",required = false) MultipartFile image,
                         @RequestParam String type){
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
                            @RequestParam(value = "type",required = false) String type){
        return docService.changeDoc(documentid,title,summary,content,image,type);
    }

    @PutMapping(value = "/changeTitle",params = {"docid","title"})
    public String changeTitle(long docid,String title){
        return null;
    }

    /*
    * 删除文章
    * */
//    @PreAuthorize(value = "hasrole={ROLE_MANAGER,ROLE_SMANAGER} ")
    @DeleteMapping("/delete/{id}")
    public String deleteDoc(@PathVariable long id){
        return docService.deleteDoc(id);
    }

    @GetMapping("typemsg")
    public int[] getTypeMsg(){
        int[] ints = new int[]{documentRepository.countAllByKindEquals("internet"),
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
