package com.example.multimedia.repository;

import com.example.multimedia.domain.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory,Long> {
    List<SearchHistory> findByUseridAndDateBefore(long id, Date date);
}
