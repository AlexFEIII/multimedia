package com.example.multimedia.repository;

import com.example.multimedia.domain.CollectDoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectDocRepository extends JpaRepository<CollectDoc,Long> {
    CollectDoc findByUseridAndDocid(long userid,long docid);
}
