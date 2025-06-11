package com.cosmobank.api.domain.repository;

import com.cosmobank.api.domain.entity.BankScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankScoreRepository extends JpaRepository<BankScoreEntity, UUID> {
}
