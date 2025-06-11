package com.cosmobank.api.domain.service;

import com.cosmobank.api.domain.entity.UserEntity;
import com.cosmobank.api.domain.repository.UserRepository;
import com.cosmobank.api.dto.GetUserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity getUser(UUID userid){
        UserEntity userEntity = this.userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));

        String userName = userEntity.getName();
        String userCPF = userEntity.getCpf();
        String userMail = userEntity.getEmail();
        String userNumber = userEntity.getNumber();
        BigDecimal userBalance = userEntity.getBalance();
        Integer userGeneralScore = userEntity.getGeneral_score();
        Boolean userCPFKey = userEntity.getCpf_key_pix();
        Boolean userEmailKey = userEntity.getEmail_key_pix();
        Boolean userNumberKey = userEntity.getNumber_key_pix();

        return ResponseEntity.ok(new GetUserResponseDTO(userName, userCPF, userMail, userNumber, userBalance, userGeneralScore, userCPFKey, userEmailKey, userNumberKey));
    }
}