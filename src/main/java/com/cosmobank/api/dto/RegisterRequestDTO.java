package com.cosmobank.api.dto;

import org.springframework.web.multipart.MultipartFile;

public record RegisterRequestDTO(String name, String cpf, String number, String email, String password, MultipartFile image, Long date) {
}
