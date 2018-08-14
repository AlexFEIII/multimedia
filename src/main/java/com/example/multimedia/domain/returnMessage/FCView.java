package com.example.multimedia.domain.returnMessage;

import java.util.List;
import java.util.Map;

public class FCView {
    private int totalPage;
    private Map<ForumCUser, List<ForumRUser>> maps;

    public FCView(){}
    public FCView(int totalPage,Map<ForumCUser, List<ForumRUser>> maps){
        this.totalPage = totalPage;
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "FCView{" +
                "totalPage=" + totalPage +
                ", maps=" + maps +
                '}';
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Map<ForumCUser, List<ForumRUser>> getMaps() {
        return maps;
    }

    public void setMaps(Map<ForumCUser, List<ForumRUser>> maps) {
        this.maps = maps;
    }
}
