package com.example.multimedia.repository;

import com.example.multimedia.domain.VideoComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoCommentRepository extends JpaRepository<VideoComment,Long> {
    List<VideoComment> findByVideoid(long id);
    Page<VideoComment> findByVideoid(long id,Pageable pageable);
}
