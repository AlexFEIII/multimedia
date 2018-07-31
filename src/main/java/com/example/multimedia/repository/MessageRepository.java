package com.example.multimedia.repository;

import com.example.multimedia.domain.returnMessage.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
