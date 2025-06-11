package com.cosmobank.api.domain.repository;

import com.cosmobank.api.domain.entity.BankScoreEntity;
import com.cosmobank.api.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BankScoreRepository extends JpaRepository<BankScoreEntity, UUID> {
    Optional<BankScoreEntity> findByuserId(UserEntity user_id);
}
