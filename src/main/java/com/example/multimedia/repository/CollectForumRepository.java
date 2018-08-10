package com.example.multimedia.repository;

import com.example.multimedia.domain.CollectForum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectForumRepository extends JpaRepository<CollectForum,Long> {
    CollectForum findByUseridAndForumid(long userid,long forumid);
}
