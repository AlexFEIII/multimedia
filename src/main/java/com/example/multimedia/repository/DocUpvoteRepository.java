package com.example.multimedia.repository;

import com.example.multimedia.domain.Docupvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocUpvoteRepository extends JpaRepository<Docupvote,Long> {
    Docupvote findByDocidAndAndUserid(long docid,long userid);
}
