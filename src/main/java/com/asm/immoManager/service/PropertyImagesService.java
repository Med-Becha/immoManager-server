package com.asm.immoManager.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface PropertyImagesService {

    public String uploadPropertyImages(MultipartFile file) throws IOException;

    public byte[] downloadPropertyImages(String images) throws IOException;
}
