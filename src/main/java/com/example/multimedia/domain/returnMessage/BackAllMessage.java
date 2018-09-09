package com.example.multimedia.domain.returnMessage;

import java.util.List;

public class BackAllMessage {
    private long docnum;
    private long pronum;
    private long videonuum;
    private long usernum;
    //男性用户数目，女性,未知
    private List<Long> mannum;
    //文章类别分布
    private List<Long> dtypenum;
    //视频类别分布
    private List<Long> vtypenum;
    //用户访问次数(两周)
    private List<Long> sawnum;

    public BackAllMessage(){}
    public BackAllMessage(long docnum,long pronum,long videonum,long usernum,List<Long> mannum,List<Long> dtypenum,List<Long> vtypenum,List<Long> sawnum){
        this.docnum = docnum;
        this.pronum = pronum;
        this.usernum = usernum;
        this.mannum = mannum;
        this.dtypenum = dtypenum;
        this.vtypenum = vtypenum;
        this.sawnum = sawnum;
    }

    public long getDocnum() {
        return docnum;
    }

    public void setDocnum(long docnum) {
        this.docnum = docnum;
    }

    public long getPronum() {
        return pronum;
    }

    public void setPronum(long pronum) {
        this.pronum = pronum;
    }

    public long getVideonuum() {
        return videonuum;
    }

    public void setVideonuum(long videonuum) {
        this.videonuum = videonuum;
    }

    public long getUsernum() {
        return usernum;
    }

    public void setUsernum(long usernum) {
        this.usernum = usernum;
    }

    public List<Long> getMannum() {
        return mannum;
    }

    public void setMannum(List<Long> mannum) {
        this.mannum = mannum;
    }

    public List<Long> getDtypenum() {
        return dtypenum;
    }

    public void setDtypenum(List<Long> dtypenum) {
        this.dtypenum = dtypenum;
    }

    public List<Long> getVtypenum() {
        return vtypenum;
    }

    public void setVtypenum(List<Long> vtypenum) {
        this.vtypenum = vtypenum;
    }

    public List<Long> getSawnum() {
        return sawnum;
    }

    public void setSawnum(List<Long> sawnum) {
        this.sawnum = sawnum;
    }
}
