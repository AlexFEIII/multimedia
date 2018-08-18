package com.example.multimedia.repository;

import com.example.multimedia.domain.DocCUpvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocCUpvoteRepository extends JpaRepository<DocCUpvote,Long> {
    DocCUpvote findByCommentidAndUserid(long commentid,long userid);
    void deleteAllByCommentid(long id);
}
