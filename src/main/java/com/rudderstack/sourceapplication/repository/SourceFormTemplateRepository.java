package com.rudderstack.sourceapplication.repository;

import com.rudderstack.sourceapplication.entity.SourceFormTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourceFormTemplateRepository extends JpaRepository<SourceFormTemplateEntity, Long> {
    boolean existsByType(String type);


    Optional<SourceFormTemplateEntity> findByType(String type);

}
