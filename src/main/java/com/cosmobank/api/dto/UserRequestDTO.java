package com.cosmobank.api.dto;

import java.math.BigDecimal;

public record UserRequestDTO(String name, BigDecimal balance) {
}
