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
    private TransportClient client;
    @Autowired
    private UserRepository userRepository;

    /*
    * 搜索用户（根据用户名搜索）
    * */
    @Override
    public Map<String,Map<String,Object>> searchUser(String key){
        SortOrder sortOrder = SortOrder.DESC;
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("username",key))
                .should(QueryBuilders.wildcardQuery("upinyin",key));
        return doSearch("muluser",queryBuilder,200,"username");
    }

    /*
    * 搜索文章（根据标题和概要搜索）//type:doc,forum
    * */
    @Override
    public Map<String,Map<String,Object>> searchDoc(String key){
        //标题搜索
        SortOrder sortOrder = SortOrder.DESC;
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title",key))
                .should(QueryBuilders.wildcardQuery("summary",key))
                .should(QueryBuilders.wildcardQuery("tpinyin",key));
        return doSearch("document",queryBuilder,400,"title");
    }

    /*
    * 搜索视频（根据视频名称搜索）
    * */
    @Override
    public Map<String,Map<String,Object>> searchVideo(String key){
        SortOrder sortOrder = SortOrder.DESC;
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title",key))
                .should(QueryBuilders.wildcardQuery("tpinyin",key))
                .should(QueryBuilders.wildcardQuery("summary",key));
        return doSearch("video",queryBuilder,200,"title");
    }

    /*
    * 执行搜索的方法
    * */
    private Map<String,Map<String,Object>> doSearch(String typeName,QueryBuilder queryBuilder,int size,String obj){
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style='color:red;'>");
        highlightBuilder.postTags("</span>");
        highlightBuilder.field(obj);
        SearchResponse response = client.prepareSearch("multimedia")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(typeName)
                .setScroll(new TimeValue(60000))
                .setQuery(queryBuilder)
                .addSort("@timestamp",SortOrder.DESC)
                .highlighter(highlightBuilder)
                .setSize(size)
                .execute().actionGet();
        Map<String,Map<String,Object>> map = new HashMap<>();
        boolean flag = false;
        if (typeName.equals("video") || typeName.equals("document") || typeName.equals("forum")) flag = true;
        int i = 0;
        for (SearchHit hit : response.getHits()){
            Text[] texts = hit.getHighlightFields().get(obj).getFragments();
            StringBuilder builder = new StringBuilder();
            for (Text str:texts)
                builder.append(str);
            Map<String,Object> objectMap = hit.getSource();
            objectMap.put("highlight",builder.toString());
            if (flag) objectMap.put("nickname",userRepository.findOne(Long.valueOf(objectMap.get("userid").toString())).getNickname());
            map.put(String.valueOf(i++),hit.getSource());
        }
        return map;
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
