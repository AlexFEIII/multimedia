package com.example.multimedia.repository;

import com.example.multimedia.domain.Forumupvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumUpvoteRepository extends JpaRepository<Forumupvote,Long> {
    Forumupvote findByForumidAndUserid(long forumid,long userid);
}
