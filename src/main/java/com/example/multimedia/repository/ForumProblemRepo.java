package com.example.multimedia.repository;

import com.example.multimedia.domain.ForumProblem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumProblemRepo extends JpaRepository<ForumProblem,Long> {
}
