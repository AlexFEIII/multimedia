package com.example.multimedia.repository;

import com.example.multimedia.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CodeRepository extends JpaRepository<Code,Long> {
    List<Code> findByPhoneNumEqualsAndDateAfter(String phone, Date date);
    List<Code> findByDateBefore(Date date);
}
