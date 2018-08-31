package com.example.multimedia.repository;

import com.example.multimedia.domain.CollectFPro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectFProRepository extends JpaRepository<CollectFPro,Long> {
    CollectFPro findByCommentidAndUserid(long cid, long uid);
    long countAllById(long id);
}
