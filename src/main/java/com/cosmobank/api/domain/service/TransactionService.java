package com.cosmobank.api.domain.service;

import com.cosmobank.api.domain.entity.UserEntity;
import com.cosmobank.api.domain.repository.UserRepository;
import com.cosmobank.api.dto.PixRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final UserRepository userRepository;

    public ResponseEntity pixTransaction(PixRequestDTO body){
        UserEntity senderEntity = this.userRepository.findById(body.userId()).orElseThrow(() -> new RuntimeException("User not found"));

        if(senderEntity.getBalance().compareTo(body.pixAmount()) < 0){
            return ResponseEntity.badRequest().body("No sufficient amount in account");
        }

        switch (body.pixType()){
            case "email": {
                UserEntity receiverEntity = this.userRepository.findByEmail(body.pixKey()).orElseThrow(() -> new RuntimeException("user not found"));

                senderEntity.setBalance(senderEntity.getBalance().subtract(body.pixAmount()));
                receiverEntity.setBalance(receiverEntity.getBalance().add(body.pixAmount()));
                break;
            }
            case "cpf": {
                UserEntity receiverEntity = this.userRepository.findByCpf(body.pixKey()).orElseThrow(() -> new RuntimeException("user not found"));

                senderEntity.setBalance(senderEntity.getBalance().subtract(body.pixAmount()));
                receiverEntity.setBalance(receiverEntity.getBalance().add(body.pixAmount()));
                break;
            }
            case "number": {
                UserEntity receiverEntity = this.userRepository.findByNumber(body.pixKey()).orElseThrow(() -> new RuntimeException("user not found"));

                senderEntity.setBalance(senderEntity.getBalance().subtract(body.pixAmount()));
                receiverEntity.setBalance(receiverEntity.getBalance().add(body.pixAmount()));
                break;
            }
            default: return ResponseEntity.badRequest().body("Key not found");
        }
        return ResponseEntity.ok("Transaction made successfully!");
    }

}
