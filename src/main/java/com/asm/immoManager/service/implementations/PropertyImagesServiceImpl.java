package com.asm.immoManager.service.implementations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.asm.immoManager.entity.PropertyImages;
import com.asm.immoManager.repository.PropertyImagesRepository;
import com.asm.immoManager.service.PropertyImagesService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PropertyImagesServiceImpl implements PropertyImagesService {

    PropertyImagesRepository propertyImagesRepository;

    private final String IMAGES_PATH = "C:\\Users\\besha\\Desktop\\immoManager\\server\\immoManager\\images\\propertyImages";

    @Override
    public String uploadPropertyImages(MultipartFile file) throws IOException {
        String imagePath = IMAGES_PATH + file.getOriginalFilename();

        PropertyImages propertyImages = propertyImagesRepository.save(
                PropertyImages.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .imagePath(imagePath)
                        .build());

        File imgFile = new File(imagePath);
        file.transferTo(imgFile);

        if (propertyImages != null) {
            return "File uploaded successfully: " + file.getOriginalFilename();
        }
        return "File upload failed";
    }

    @Override
    public byte[] downloadPropertyImages(String fileName) throws IOException {
        Optional<PropertyImages> dbPropertyImages = propertyImagesRepository.findByName(fileName);
        if (dbPropertyImages.isPresent()) {
            String imagePath = dbPropertyImages.get().getImagePath();
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                return Files.readAllBytes(imgFile.toPath());
            } else {
                throw new IOException("Image not found on disk with path: " + imagePath);
            }
        } else {
            throw new IOException("Image not found in database with name: " + fileName);
        }
    }
}
