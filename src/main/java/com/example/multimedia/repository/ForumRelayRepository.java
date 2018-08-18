package com.example.multimedia.repository;

import com.example.multimedia.domain.ForumRelay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumRelayRepository extends JpaRepository<ForumRelay,Long> {
    List<ForumRelay> findByCommentidAndRcommentidEquals(long id,long rid);
    List<ForumRelay> findByCommentidAndRcommentid(long cid, long rid);
    List<ForumRelay> findByCommentid(long cid);
    List<ForumRelay> findByRcommentid(long id);
    void deleteAllByCommentid(long id);
}
