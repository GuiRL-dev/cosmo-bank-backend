package com.cosmobank.api.domain.service;

import com.cosmobank.api.domain.entity.UserEntity;
import com.cosmobank.api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final UserRepository userRepository;

    public ResponseEntity pixTransaction(UUID senderId, String pixType, String pixKey, BigDecimal amount){
        UserEntity senderEntity = this.userRepository.findById(senderId).orElseThrow(() -> new RuntimeException("User not found"));

        if(senderEntity.getBalance().compareTo(amount) < 0){
            return ResponseEntity.badRequest().body("No sufficient amount in account");
        }

        switch (pixType){
            case "email": {
                UserEntity receiverEntity = this.userRepository.findByEmail(pixKey).orElseThrow(() -> new RuntimeException("user not found"));

                senderEntity.setBalance(senderEntity.getBalance().subtract(amount));
                receiverEntity.setBalance(receiverEntity.getBalance().add(amount));
                break;
            }
            case "cpf": {
                UserEntity receiverEntity = this.userRepository.findByCpf(pixKey).orElseThrow(() -> new RuntimeException("user not found"));

                senderEntity.setBalance(senderEntity.getBalance().subtract(amount));
                receiverEntity.setBalance(receiverEntity.getBalance().add(amount));
                break;
            }
            case "number": {
                UserEntity receiverEntity = this.userRepository.findByNumber(pixKey).orElseThrow(() -> new RuntimeException("user not found"));

                senderEntity.setBalance(senderEntity.getBalance().subtract(amount));
                receiverEntity.setBalance(receiverEntity.getBalance().add(amount));
                break;
            }
            default: return ResponseEntity.badRequest().body("Key not found");
        }
        return ResponseEntity.ok("Transaction made successfully!");
    }

}
