package com.cosmobank.api.dto;

import java.math.BigDecimal;

public record GetUserResponseDTO(String name, String cpf, String email, String number, BigDecimal balance,
                                 Integer general_score, Boolean cpf_key_pix, Boolean email_key_pix, Boolean number_key_pix) {
}
