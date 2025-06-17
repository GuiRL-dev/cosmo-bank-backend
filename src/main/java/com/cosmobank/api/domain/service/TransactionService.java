package com.cosmobank.api.domain.service;

import com.cosmobank.api.domain.entity.UserEntity;
import com.cosmobank.api.domain.repository.UserRepository;
import com.cosmobank.api.dto.PixRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity pixTransaction(PixRequestDTO body){
        UserEntity senderEntity = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found 1"));

        if(senderEntity.getBalance().compareTo(body.pixAmount()) < 0){
            return ResponseEntity.badRequest().body("No sufficient amount in account");
        }

        switch (body.pixType()){
            case "email": {
                UserEntity receiverEntity = this.userRepository.findByEmail(body.pixKey()).orElseThrow(() -> new RuntimeException("user not found"));

                senderEntity.setBalance(senderEntity.getBalance().subtract(body.pixAmount()));
                receiverEntity.setBalance(receiverEntity.getBalance().add(body.pixAmount()));
                this.userRepository.save(senderEntity);
                this.userRepository.save(receiverEntity);
                break;
            }
            case "cpf": {
                UserEntity receiverEntity = this.userRepository.findByCpf(body.pixKey()).orElseThrow(() -> new RuntimeException("user not found"));

                senderEntity.setBalance(senderEntity.getBalance().subtract(body.pixAmount()));
                receiverEntity.setBalance(receiverEntity.getBalance().add(body.pixAmount()));
                this.userRepository.save(senderEntity);
                this.userRepository.save(receiverEntity);
                break;
            }
            case "number": {
                UserEntity receiverEntity = this.userRepository.findByNumber(body.pixKey()).orElseThrow(() -> new RuntimeException("user not found 2"));

                senderEntity.setBalance(senderEntity.getBalance().subtract(body.pixAmount()));
                receiverEntity.setBalance(receiverEntity.getBalance().add(body.pixAmount()));
                this.userRepository.save(senderEntity);
                this.userRepository.save(receiverEntity);
                break;
            }
            default: return ResponseEntity.badRequest().body("Key not found");
        }
        return ResponseEntity.ok("Transaction made successfully!");
    }

}
