package com.example.multimedia.repository;

import com.example.multimedia.domain.DocRecycler;
import com.example.multimedia.domain.DocRelay;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.util.List;

public interface DocRelayRepository extends JpaRepository<DocRelay,Long> {
    List<DocRelay> findByCommentidAndRcommentidEquals(long id,long rid);
    List<DocRelay> findByCommentidAndRcommentid(long cid,long rid);
    List<DocRelay> findByCommentid(long cid);
    List<DocRelay> findByRcommentid(long id);
    void deleteAllByCommentid(long id);
}
