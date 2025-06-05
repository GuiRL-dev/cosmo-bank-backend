package com.cosmobank.api.controller;

import com.cosmobank.api.domain.entity.UserEntity;
import com.cosmobank.api.domain.repository.UserRepository;
import com.cosmobank.api.domain.service.AzureBlobStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/cosmo-server")
@RequiredArgsConstructor
public class AzureController {
    private final AzureBlobStorageService azureBlobStorageService;
    private final UserRepository userRepository;

    @PostMapping("/upload-image")
    public ResponseEntity uploadImage(@RequestParam("imageFile") MultipartFile imageFile, String email){
        try{
            UserEntity userEntity = this.userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
            String fileName = azureBlobStorageService.upload(imageFile, userEntity.getName());
            userEntity.setImage_filename(fileName);
            this.userRepository.save(userEntity);

            return ResponseEntity.ok("Image uploaded");
        }
        catch (IOException exception) {
            return ResponseEntity.badRequest().body("Error while uploading image to server");
        }
    }
}
