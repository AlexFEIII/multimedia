package com.example.multimedia.repository;

import com.example.multimedia.domain.CollectDKind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectDKindRepository extends JpaRepository<CollectDKind,Long> {
    CollectDKind findByUseridAndKindEquals(long userid,String kind);
    int countAllByKindEquals(String kind);
}
