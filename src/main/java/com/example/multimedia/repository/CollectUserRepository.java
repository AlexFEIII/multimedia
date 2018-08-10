package com.example.multimedia.repository;

import com.example.multimedia.domain.CollectUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectUserRepository extends JpaRepository<CollectUser,Long> {
    CollectUser findByUseridAndCuserid(long userid,long cuserid);
}
