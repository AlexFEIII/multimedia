package com.example.multimedia.repository;

import com.example.multimedia.domain.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum,Long> {
    List<Forum> findByUserid(long id);
}
