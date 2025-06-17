package com.cosmobank.api.domain.service;

import com.cosmobank.api.domain.entity.UserEntity;
import com.cosmobank.api.domain.repository.UserRepository;
import com.cosmobank.api.dto.GetUserByPixRequestDTO;
import com.cosmobank.api.dto.GetUserByPixResponseDTO;
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

    public ResponseEntity getUser(String useremail){
        UserEntity userEntity = this.userRepository.findByEmail(useremail).orElseThrow(() -> new RuntimeException("User not found"));

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

    public ResponseEntity getPixUser(String usernumber){
        String pixkey = "number";

        switch (pixkey) {
            case "email": {
                UserEntity userEntity = this.userRepository.findByEmail(usernumber).orElseThrow(() -> new RuntimeException("user not found"));
                if (userEntity.getEmail_key_pix()) {
                    return ResponseEntity.badRequest().body("Unregistered key");
                }
                return ResponseEntity.ok(new GetUserByPixResponseDTO(userEntity.getName(), userEntity.getCpf()));
            }
            case "cpf": {
                UserEntity userEntity = this.userRepository.findByCpf(usernumber).orElseThrow(() -> new RuntimeException("user not found"));
                if (userEntity.getCpf_key_pix()) {
                    return ResponseEntity.badRequest().body("Unregistered key");
                }
                return ResponseEntity.ok(new GetUserByPixResponseDTO(userEntity.getName(), userEntity.getCpf()));
            }
            case "number": {
                UserEntity userEntity = this.userRepository.findByNumber(usernumber).orElseThrow(() -> new RuntimeException("user not found"));
                if (userEntity.getNumber_key_pix()) {
                    return ResponseEntity.badRequest().body("Unregistered key");
                }
                return ResponseEntity.ok(new GetUserByPixResponseDTO(userEntity.getName(), userEntity.getCpf()));
            }
            default:
                return ResponseEntity.badRequest().body("Not valid type of key");
        }
    }
    public ResponseEntity activateEmailKeyPix(UUID userId){
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

        userEntity.setEmail_key_pix(Boolean.TRUE);
        return ResponseEntity.ok("Key activated");
    }
    public ResponseEntity activateCpfKeyPix(UUID userId){
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

        userEntity.setCpf_key_pix(Boolean.TRUE);
        return ResponseEntity.ok("Key activated");
    }
    public ResponseEntity activateNumberKeyPix(UUID userId){
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

        userEntity.setNumber_key_pix(Boolean.TRUE);
        return ResponseEntity.ok("Key activated");
    }
}