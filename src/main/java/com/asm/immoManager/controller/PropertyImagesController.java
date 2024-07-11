package com.asm.immoManager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asm.immoManager.service.PropertyImagesService;

import lombok.AllArgsConstructor;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class PropertyImagesController {

    PropertyImagesService propertyImagesService;

    @PostMapping()
    public ResponseEntity<String> postPropertyImages(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadImage = propertyImagesService.uploadPropertyImages(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> getPropertyImages(@PathVariable String fileName) throws IOException {
        byte[] imagesData = propertyImagesService.downloadPropertyImages(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(imagesData);
    }
}
