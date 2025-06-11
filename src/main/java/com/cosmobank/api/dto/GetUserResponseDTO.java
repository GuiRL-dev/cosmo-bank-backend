package com.cosmobank.api.dto;

import java.math.BigDecimal;

public record GetUserResponseDTO(String Name, String Cpf, String Email, String Number, BigDecimal Balance,
                                 Integer General_score, Boolean Cpf_key_pix, Boolean Email_key_pix, Boolean Number_key_pix) {
}
