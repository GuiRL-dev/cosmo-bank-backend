package com.cosmobank.api.dto;

public record RegisterRequestDTO(String name, String cpf, String number, String email, String password, Long date) {
}
