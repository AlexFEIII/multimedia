package com.example.multimedia.repository;

import com.example.multimedia.domain.ForumHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ForumHistoryRepository extends JpaRepository<ForumHistory,Long> {
    List<ForumHistory> findByUseridAndDateBefore(long id, Date date);
    List<ForumHistory> findByUseridOrderByIdDesc(long id);
    List<ForumHistory> findByUseridAndForumid(long uid,long fid);
}
