package com.cosmobank.api.controller;

import com.cosmobank.api.domain.entity.UserEntity;
import com.cosmobank.api.domain.repository.UserRepository;
import com.cosmobank.api.domain.service.AzureBlobStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/cosmo-server")
@RequiredArgsConstructor
public class AzureController {
    @Autowired
    private final AzureBlobStorageService azureBlobStorageService;
    private final UserRepository userRepository;

    @PutMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile imageFile, UUID userId){
        try{
            UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            String fileName = azureBlobStorageService.upload(imageFile, userEntity.getName());
            userEntity.setImage_filename(fileName);
            this.userRepository.save(userEntity);

            return ResponseEntity.ok("Image uploaded");
        }
        catch (IOException exception) {
            return ResponseEntity.badRequest().body("Error while uploading image to server");
        }
    }

    @GetMapping("/get-image/{filename}")
    public ResponseEntity<String> getImage(@PathVariable String filename){
        try {
            String url = azureBlobStorageService.generateUrl(filename);
            return ResponseEntity.ok(url);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
