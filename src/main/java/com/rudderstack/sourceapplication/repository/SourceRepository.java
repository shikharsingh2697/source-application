package com.rudderstack.sourceapplication.repository;

import com.rudderstack.sourceapplication.entity.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<SourceEntity, Long> {
}
