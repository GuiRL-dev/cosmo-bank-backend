package com.cosmobank.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PixRequestDTO (UUID userId, String pixType, String pixKey, BigDecimal pixAmount){
}
