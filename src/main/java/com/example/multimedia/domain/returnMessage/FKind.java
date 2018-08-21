package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.Forum;
import com.example.multimedia.domain.ForumKind;
import org.springframework.data.domain.Page;

import java.util.List;

public class FKind {
    private ForumKind forumKind;
    private int totalPage;
    private Page<Forum> forums;

    public FKind(){}
    public FKind(ForumKind forumKind,int totalPage,Page<Forum> forums){
        this.forumKind = forumKind;
        this.totalPage = totalPage;
        this.forums = forums;
    }

    public ForumKind getForumKind() {
        return forumKind;
    }

    public void setForumKind(ForumKind forumKind) {
        this.forumKind = forumKind;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Page<Forum> getForums() {
        return forums;
    }

    public void setForums(Page<Forum> forums) {
        this.forums = forums;
    }
}
