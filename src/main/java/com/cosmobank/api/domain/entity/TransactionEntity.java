package com.cosmobank.api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "transactions")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity senderUser;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiverUser;
    private String used_key;
    private Integer amount;
    private Date transaction_timestamp;
    private String status;
}
