package com.example.multimedia.repository;

import com.example.multimedia.domain.Videoupvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoUpvoteRepository extends JpaRepository<Videoupvote,Long> {
    Videoupvote findByVideoidAndUserid(long videoid,long userid);
}
