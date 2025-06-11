package com.cosmobank.api.controller;


import com.cosmobank.api.domain.service.UserService;
import com.cosmobank.api.dto.GetUserByPixRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get/{userid}")
    public ResponseEntity getUser(@PathVariable UUID userid){
        return userService.getUser(userid);
    }

    @GetMapping("/getbypix")
    public ResponseEntity getUserByPix(@RequestBody GetUserByPixRequestDTO body){
        return userService.getPixUser(body);
    }
}
