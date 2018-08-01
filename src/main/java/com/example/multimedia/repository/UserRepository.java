package com.example.multimedia.repository;

        import com.example.multimedia.domain.MulUser;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.domain.Sort;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;

        import javax.persistence.*;
        import javax.persistence.criteria.CriteriaBuilder;
        import javax.persistence.metamodel.Metamodel;
        import java.util.Map;

public interface UserRepository extends JpaRepository<MulUser,Long> {
        MulUser findByUsername(String username);
        MulUser findByEmail(String email);
        Page<MulUser> findAll(Pageable pageable);
}
