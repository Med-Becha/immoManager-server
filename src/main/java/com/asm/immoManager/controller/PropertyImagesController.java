package com.asm.immoManager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asm.immoManager.entity.PropertyImages;
import com.asm.immoManager.service.PropertyImagesService;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class PropertyImagesController {

    PropertyImagesService propertyImagesService;

    @PostMapping("{propertyid}")
    public ResponseEntity<String> postPropertyImages(@PathVariable Long propertyid,
            @RequestParam("file") MultipartFile file) throws IOException {
        String uploadImage = propertyImagesService.uploadPropertyImages(propertyid, file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> getPropertyImages(@PathVariable String fileName) throws IOException {
        byte[] imagesData = propertyImagesService.downloadPropertyImages(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(imagesData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePropertyImage(@PathVariable Long id) throws IOException {
        String response = propertyImagesService.deletePropertyImage(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all/{propertyId}")
    public ResponseEntity<?> getAllPropertyImages(@PathVariable Long propertyId) {
        List<PropertyImages> images = propertyImagesService.getAllPropertyImages(propertyId);
        return ResponseEntity.status(HttpStatus.OK).body(images);
    }
}
