package com.cosmobank.api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "reports")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity {

    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "reported_id")
    private UserEntity reportedUser;
    @ManyToOne
    @JoinColumn(name = "whistleblower_id")
    private UserEntity whistleblowerUser;
    @ManyToOne
    @JoinColumn(name = "origin_transaction_id")
    private TransactionEntity originTransaction;
    private String reason;
    private String description;
    private Date report_timestamp;
    private String status;
}
