package com.example.multimedia.repository;

import com.example.multimedia.domain.Forum;
import com.example.multimedia.domain.ForumKind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumKindRepository extends JpaRepository<ForumKind,Integer> {
    ForumKind findByKindEquals(String kind);
}
