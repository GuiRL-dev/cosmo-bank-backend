package com.cosmobank.api.controller;

import com.cosmobank.api.domain.service.TransactionService;
import com.cosmobank.api.dto.PixRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/pix")
    public ResponseEntity pixTransaction(@RequestBody PixRequestDTO body){
        return transactionService.pixTransaction(body);
    }
}
