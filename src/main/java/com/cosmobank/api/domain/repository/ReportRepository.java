package com.cosmobank.api.domain.repository;

import com.cosmobank.api.domain.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<ReportEntity, UUID> {
}
