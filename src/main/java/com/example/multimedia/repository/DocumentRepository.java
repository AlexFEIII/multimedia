package com.example.multimedia.repository;

import com.example.multimedia.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    List<Document> findByUseridAndKindOrderByDateAsc(long id,String type);
}
