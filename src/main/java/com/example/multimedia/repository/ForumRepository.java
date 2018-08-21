package com.example.multimedia.repository;

import com.example.multimedia.domain.Forum;
import com.example.multimedia.domain.ForumKind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum,Long> {
    List<Forum> findByUseridOrderByDateAsc(long id);
    long countAllByKindEquals(ForumKind kind);
    Page<Forum> findByKindEquals(ForumKind kind, Pageable pageable);
}
