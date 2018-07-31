package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.repository.UserRepository;
import com.example.multimedia.service.SearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryShardContext;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.swing.text.Highlighter;
import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransportClient client;

    /*
    * 搜索用户（根据用户名搜索）
    * */
    @Override
    public Map<String,Map<String,Object>> searchUser(String key,String obj,String sort){
        String k = getKey(key);
        SortOrder sortOrder = SortOrder.DESC;
        if (sort.equals("asc"))
            sortOrder = SortOrder.ASC;
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.wildcardQuery("username",k))
                .should(QueryBuilders.wildcardQuery("upinyin",k));
        return doSearch("muluser",queryBuilder,200,obj,sortOrder);
    }

    /*
    * 搜索文章（根据标题和概要搜索）//type:doc,forum
    * */
    @Override
    public Map<String,Map<String,Object>> searchDoc(String type,String key,String obj,String sort){
        //标题搜索
        SortOrder sortOrder = SortOrder.DESC;
        if (sort.equals("asc"))
            sortOrder = SortOrder.ASC;
        String k = getKey(key);
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.wildcardQuery("title",k))
                .should(QueryBuilders.wildcardQuery("summary",k))
                .should(QueryBuilders.wildcardQuery("tpinyin",k));
        return doSearch(type,queryBuilder,400,obj,sortOrder);
    }

    /*
    * 搜索视频（根据视频名称搜索）
    * */
    @Override
    public Map<String,Map<String,Object>> searchVideo(String key,String obj,String sort){
        SortOrder sortOrder = SortOrder.DESC;
        if (sort.equals("asc"))
            sortOrder = SortOrder.ASC;
        String k = getKey(key);
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.wildcardQuery("title",k))
                .should(QueryBuilders.wildcardQuery("tpinyin",k))
                .should(QueryBuilders.wildcardQuery("summary",k));
        return doSearch("video",queryBuilder,200,obj,sortOrder);
    }

    /*
    * 执行搜索的方法
    * */
    private Map<String,Map<String,Object>> doSearch(String typeName,QueryBuilder queryBuilder,int size,String obj,SortOrder order){
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.preTags("<strong>");
//        highlightBuilder.postTags("</strong>");
//        highlightBuilder.field("username");
        SearchResponse response = client.prepareSearch("multimedia")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(typeName)
                .setScroll(new TimeValue(60000))
                .setQuery(queryBuilder)
                .addSort(obj,order)
//                .highlighter(highlightBuilder)
                .setSize(size)
                .execute().actionGet();
        Map<String,Map<String,Object>> map = new HashMap<>();
        int i = 0;
        for (SearchHit hit : response.getHits()){
            map.put(String.valueOf(i++),hit.getSource());
            System.out.println(hit.getHighlightFields());
//            Text[] texts = hit.getHighlightFields().get("username").getFragments();
//            for (Text str : texts) System.out.println(str);
        }
        return map;
    }

    /*
    * 加工关键字
    * */
    private String getKey(String key){
        StringBuffer stringBuffer = new StringBuffer(key);
        for (int i = 0;i < stringBuffer.length()+1;i += 2){
            stringBuffer.insert(i,"*");
        }
        return stringBuffer.toString();
    }

    @Override
    public Map<String,Object> getUser() {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        QueryBuilder queryBuilder = QueryBuilders.termQuery("username",username);
        SearchResponse response = client.prepareSearch("multimedia")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes("muluser")
                .setScroll(new TimeValue(60000))
                .setQuery(queryBuilder)
                .execute().actionGet();
        SearchHit hit = response.getHits().getHits()[0];
        return hit.getSource();
    }
}
