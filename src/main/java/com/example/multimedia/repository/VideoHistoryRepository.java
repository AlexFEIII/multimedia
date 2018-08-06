package com.example.multimedia.repository;

import com.example.multimedia.domain.VideoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface VideoHistoryRepository extends JpaRepository<VideoHistory,Long> {
    List<VideoHistory> findByUseridAndDateBefore(long id, Date date);
}
