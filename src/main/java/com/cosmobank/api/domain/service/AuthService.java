package com.cosmobank.api.domain.service;

import com.cosmobank.api.domain.entity.UserEntity;
import com.cosmobank.api.domain.repository.UserRepository;
import com.cosmobank.api.dto.LoginRequestDTO;
import com.cosmobank.api.dto.RegisterRequestDTO;
import com.cosmobank.api.dto.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseEntity<TokenResponseDTO> loginUser(LoginRequestDTO body) {
        UserEntity userEntity = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), userEntity.getPassword())) {
            String token = this.tokenService.generateToken(userEntity);
            return ResponseEntity.ok(new TokenResponseDTO(userEntity.getEmail(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> registerUser(RegisterRequestDTO body){
        Optional<UserEntity> user = this.userRepository.findByEmail(body.email());

        if(user.isEmpty()){
            UserEntity newUserEntity = new UserEntity();
            newUserEntity.setPassword(passwordEncoder.encode(body.password()));
            newUserEntity.setEmail(body.email());
            newUserEntity.setName(body.name());
            newUserEntity.setCpf(body.cpf());
            newUserEntity.setNumber(body.number());
            newUserEntity.setBalance(new BigDecimal("100"));
            newUserEntity.setGeneral_score(500);
            newUserEntity.setBank_score(700);
            newUserEntity.setUpdate_date(Date.from(actualTimestamp()));
            newUserEntity.setCreation_date(Date.from(actualTimestamp()));
            newUserEntity.setImage_filename("");
            newUserEntity.setCpf_key_pix(false);
            newUserEntity.setEmail_key_pix(false);
            newUserEntity.setNumber_key_pix(false);
            this.userRepository.save(newUserEntity);

            String token = this.tokenService.generateToken(newUserEntity);
            return ResponseEntity.ok(new TokenResponseDTO(newUserEntity.getEmail(), token));
        }
        return ResponseEntity.badRequest().body("Email already exists in database");
    }

    private Instant actualTimestamp() {
        return Instant.now();
    }

}
