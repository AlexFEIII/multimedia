package com.example.multimedia.repository;

import com.example.multimedia.domain.CollectFComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectFCRepository extends JpaRepository<CollectFComment,Long> {
    CollectFComment findByCommentidAndUserid(long cid,long uid);
}
