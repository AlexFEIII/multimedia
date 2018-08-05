package com.example.multimedia.repository;

import com.example.multimedia.domain.VideoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoHistoryRepository extends JpaRepository<VideoHistory,Long> {
}
