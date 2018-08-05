package com.example.multimedia.repository;

import com.example.multimedia.domain.DocHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocHistoryRepository extends JpaRepository<DocHistory,Long> {
    List<DocHistory> findByUserid(long id);
}
