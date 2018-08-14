package com.example.multimedia.domain.returnMessage;

import java.util.List;
import java.util.Map;

public class VCView {
    private int totalPage;
    private Map<VideoCUser, List<VideoRUser>> maps;

    public VCView(){}
    public VCView(int totalPage,Map<VideoCUser, List<VideoRUser>> maps){
        this.totalPage = totalPage;
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "VCView{" +
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

    public Map<VideoCUser, List<VideoRUser>> getMaps() {
        return maps;
    }

    public void setMaps(Map<VideoCUser, List<VideoRUser>> maps) {
        this.maps = maps;
    }
}
