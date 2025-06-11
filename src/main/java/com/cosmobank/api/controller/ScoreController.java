package com.cosmobank.api.controller;

import com.cosmobank.api.domain.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;

    @GetMapping("/status/{userid}")
    public ResponseEntity getUserScoreStatus(@PathVariable UUID userid){
        return scoreService.userGeneralScoreCheck(userid);
    }
}
