package com.cosmobank.api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Table(name = "general_score")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralScoreEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;
    private String reason;
    private BigDecimal changed_value;
    private Date timestamp;
}
