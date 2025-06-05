package com.cosmobank.api.dto;

public record RegisterRequestDTO(String name, String cpf, Integer number, String email, String password) {
}
