package com.example.multimedia.repository;

import com.example.multimedia.domain.DocRecycler;
import com.example.multimedia.domain.DocRelay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocRelayRepository extends JpaRepository<DocRelay,Long> {
    List<DocRelay> findByCommentidAndRcommentidEquals(long id,long rid);
    List<DocRelay> findByCommentidAndRcommentid(long cid,long rid);
}
