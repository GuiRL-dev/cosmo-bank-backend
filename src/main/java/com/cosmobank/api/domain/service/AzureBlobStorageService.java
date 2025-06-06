package com.cosmobank.api.domain.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.OffsetDateTime;


@Service
public class AzureBlobStorageService {

    @Autowired
    private BlobContainerClient blobContainerClient;

    public String upload(MultipartFile file, String name) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("The file is empty");
        }

        String uniqueFilename = name + "-image-file";

        BlobClient blobClient = blobContainerClient.getBlobClient(uniqueFilename);
        BlobHttpHeaders headers = new BlobHttpHeaders();

        headers.setContentType(file.getContentType());
        blobClient.upload(file.getInputStream(), file.getSize(), true);
        blobClient.setHttpHeaders(headers);

        return uniqueFilename;
    }

    public String generateUrl(String filename){

        BlobClient blobClient = blobContainerClient.getBlobClient(filename);

        if(!blobClient.exists()){
            throw new RuntimeException("File: " + filename + " not found");
        }

        BlobSasPermission sasPermission = new BlobSasPermission().setReadPermission(true);
        OffsetDateTime expiryTime = OffsetDateTime.now().plusMinutes(15);
        BlobServiceSasSignatureValues sasValues = new BlobServiceSasSignatureValues(expiryTime, sasPermission);

        String sasToken = blobClient.generateSas(sasValues);

        return String.format("%s?%s",blobClient.getBlobUrl(), sasToken);
    }
}
