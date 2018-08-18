package com.example.multimedia.repository;

import com.example.multimedia.domain.VideoCUpvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCUpvoteRepository extends JpaRepository<VideoCUpvote,Long> {
    VideoCUpvote findByCommentidAndUserid(long commentid,long userid);
    void deleteAllByCommentid(long id);
}
