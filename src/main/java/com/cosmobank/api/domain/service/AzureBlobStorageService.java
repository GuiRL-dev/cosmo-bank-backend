package com.cosmobank.api.domain.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


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
}
