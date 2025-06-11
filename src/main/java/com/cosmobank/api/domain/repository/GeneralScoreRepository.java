package com.cosmobank.api.domain.repository;

import com.cosmobank.api.domain.entity.GeneralScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GeneralScoreRepository extends JpaRepository<GeneralScoreEntity, UUID> {
}
