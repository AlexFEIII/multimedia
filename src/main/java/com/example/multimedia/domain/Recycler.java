package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Recycler {
    @Id
    @GeneratedValue
    private long id;
    //删除的对象种类
    private String type;
    //删除的对象信息
    private long objid;
    //删除的时间
    private LocalDate localDate;

    public Recycler(){}

    public Recycler(String type,long objid){
        this.type = type;
        this.objid = objid;
        this.localDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Recycler{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", objid='" + objid + '\'' +
                ", localDate=" + localDate +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getObjid() {
        return objid;
    }

    public void setObjid(long objid) {
        this.objid = objid;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
