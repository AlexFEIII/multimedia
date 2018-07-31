package com.example.multimedia.repository;

import com.example.multimedia.domain.ForumCUpvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumCUpvoteRepository extends JpaRepository<ForumCUpvote,Long> {
    ForumCUpvote findByCommentidAndUserid(long commentid,long userid);
}
