package com.example.multimedia.repository;

import com.example.multimedia.domain.VideoRecycler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRecyclerRepository extends JpaRepository<VideoRecycler,Long> {
}
