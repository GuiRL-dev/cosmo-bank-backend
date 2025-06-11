package com.cosmobank.api.domain.service;

import com.cosmobank.api.domain.entity.UserEntity;
import com.cosmobank.api.domain.repository.UserRepository;
import com.cosmobank.api.domain.utils.enums.ScoreStatus;
import com.cosmobank.api.dto.UserScoreStatusResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final UserRepository userRepository;

    public ResponseEntity userGeneralScoreCheck(UUID userId){
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

        Integer userGeneralScore = userEntity.getBank_score();
        ScoreStatus response;

        if(userGeneralScore >= 600){
            response = ScoreStatus.APPROVED;
        } else if (userGeneralScore >= 350) {
            response = ScoreStatus.ALERT;
        } else {
            response = ScoreStatus.BLOCKED;
        }
        return ResponseEntity.ok(new UserScoreStatusResponseDTO(response));
    }
}
