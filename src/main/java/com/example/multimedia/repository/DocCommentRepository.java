package com.example.multimedia.repository;

import com.example.multimedia.domain.DocComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocCommentRepository extends JpaRepository<DocComment,Long> {
    List<DocComment> findByDocid(long id);
}
