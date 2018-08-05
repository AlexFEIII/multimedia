package com.example.multimedia.repository;

import com.example.multimedia.domain.ForumHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumHistoryRepository extends JpaRepository<ForumHistory,Long> {
    List<ForumHistory> findByUserid(long id);
}
