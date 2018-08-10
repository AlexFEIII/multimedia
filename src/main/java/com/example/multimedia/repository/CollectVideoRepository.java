package com.example.multimedia.repository;

import com.example.multimedia.domain.CollectVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectVideoRepository extends JpaRepository<CollectVideo,Long> {
    CollectVideo findByUseridAndVideoid(long userid,long videoid);
}
