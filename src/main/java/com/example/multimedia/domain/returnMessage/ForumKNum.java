package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.ForumKind;

public class ForumKNum {
    private ForumKind forumKind;
    private long count;

    public ForumKNum(){}
    public ForumKNum(ForumKind forumKind,long count){
        this.forumKind = forumKind;
        this.count = count;
    }

    public ForumKind getForumKind() {
        return forumKind;
    }

    public void setForumKind(ForumKind forumKind) {
        this.forumKind = forumKind;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
