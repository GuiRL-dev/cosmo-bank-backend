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

@Table(name = "users")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String cpf;
    private String email;
    private Integer number;
    private BigDecimal balance;
    private Integer general_score;
    private Integer bank_score;
    private Date update_date;
    private Date creation_date;
    private Boolean cpf_key_pix;
    private Boolean email_key_pix;
    private Boolean number_key_pix;
}
