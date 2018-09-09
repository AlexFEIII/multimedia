package com.example.multimedia.repository;

import com.example.multimedia.domain.SawCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface SawCountRepo extends JpaRepository<SawCount, Date> {
    SawCount findByDate(String date);
}
