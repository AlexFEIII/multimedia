package com.example.multimedia.repository;

import com.example.multimedia.domain.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Long> {
    List<Video> findByUseridOrderByDateAsc(long id);
}
