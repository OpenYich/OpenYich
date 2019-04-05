package com.openyich.framework.boot.id.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openyich.framework.boot.id.domain.SegmentEntity;

@Repository
public interface SegmentRepository extends JpaRepository<SegmentEntity, String> {

}
