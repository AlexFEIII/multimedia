package com.example.multimedia.repository;

import com.example.multimedia.domain.ForumProblem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumProblemRepository extends JpaRepository<ForumProblem,Long> {
   List<ForumProblem> findByForumid(long id);
   Page<ForumProblem> findByForumid(long id, Pageable pageable);
}
