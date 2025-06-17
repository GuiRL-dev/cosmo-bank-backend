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

    @GetMapping("/get")
    public ResponseEntity getUser(@RequestBody String useremail){
        return userService.getUser(useremail);
    }

    @GetMapping("/getbypix")
    public ResponseEntity getUserByPix(@RequestBody GetUserByPixRequestDTO body){
        return userService.getPixUser(body);
    }

    @PostMapping("/activatekey/email/{userid}")
    public ResponseEntity activateKeyEmail(@PathVariable UUID userid){return  userService.activateEmailKeyPix(userid);}

    @PostMapping("/activatekey/cpf/{userid}")
    public ResponseEntity activateKeyCpf(@PathVariable UUID userid){return  userService.activateCpfKeyPix(userid);}

    @PostMapping("/activatekey/number/{userid}")
    public ResponseEntity activateKeyNumber(@PathVariable UUID userid){return  userService.activateNumberKeyPix(userid);}

}
