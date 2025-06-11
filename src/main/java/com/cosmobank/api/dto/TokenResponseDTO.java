package com.cosmobank.api.dto;

import java.util.UUID;

public record TokenResponseDTO(UUID UserId, String Token) {
}
