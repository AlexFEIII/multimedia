package com.example.multimedia.repository;

import com.example.multimedia.domain.VideoRelay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRelayRepository extends JpaRepository<VideoRelay,Long> {
    List<VideoRelay> findByCommentidAndRcommentidEquals(long id, long rid);
    List<VideoRelay> findByCommentidAndRcommentid(long cid,long rid);
    List<VideoRelay> findByCommentid(long cid);
}
