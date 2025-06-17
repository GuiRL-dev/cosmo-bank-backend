package com.cosmobank.api.dto;

import java.math.BigDecimal;

public record PixRequestDTO (String email, String pixType, String pixKey, BigDecimal pixAmount){
}
