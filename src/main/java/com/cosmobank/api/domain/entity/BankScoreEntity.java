package com.cosmobank.api.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Table(name = "bank_score")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankScoreEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private UUID user_id;
    private String reason;
    private BigDecimal changed_value;
    private Date timestamp;

}
