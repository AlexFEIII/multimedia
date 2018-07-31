package com.example.multimedia.repository;

import com.example.multimedia.domain.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum,Long> {
}
