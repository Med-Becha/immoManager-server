package com.asm.immoManager.service.implementations;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.asm.immoManager.entity.Property;
import com.asm.immoManager.entity.PropertyImages;
import com.asm.immoManager.repository.PropertyImagesRepository;
import com.asm.immoManager.repository.PropertyRepository;
import com.asm.immoManager.service.PropertyImagesService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PropertyImagesServiceImpl implements PropertyImagesService {

    PropertyImagesRepository propertyImagesRepository;
    PropertyRepository propertyRepository;

    private final String IMAGES_PATH = "C:\\Users\\besha\\Desktop\\immoManager\\server\\immoManager\\images\\propertyImages\\";

    // save the image in the db with new name generated with uuid

    @Override
    @Transactional
    public String uploadPropertyImages(Long propertyId, MultipartFile file) throws IOException {
        // Proceed with file upload
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or not provided");
        }
        // Check if max images limit (3) has been reached
        List<PropertyImages> existingImages = propertyImagesRepository.findByPropertyId(propertyId);
        if (existingImages.size() >= 3) {
            throw new IOException("Maximum images limit reached for property: " + propertyId);
        }
        // Check if propertyId exists
        Optional<Property> propertyOpt = propertyRepository.findById(propertyId);
        if (!propertyOpt.isPresent()) {
            throw new IOException("Property not found: " + propertyId);
        }

        String fileExtension = getFileExtension(file.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;
        String imagePath = IMAGES_PATH + uniqueFileName;

        Property property = propertyOpt.get();

        PropertyImages propertyImages = PropertyImages.builder()
                .name(uniqueFileName)
                .type(file.getContentType())
                .imagePath(imagePath)
                .property(property)
                .build();

        propertyImagesRepository.save(propertyImages);

        File imgFile = new File(imagePath);
        file.transferTo(imgFile);

        return "File uploaded successfully: " + uniqueFileName;
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

    // remove image from db and system
    @Override
    @Transactional
    public String deletePropertyImage(Long imageId) throws IOException {
        Optional<PropertyImages> propertyImagesOptional = propertyImagesRepository.findById(imageId);
        if (propertyImagesOptional.isPresent()) {
            PropertyImages propertyImages = propertyImagesOptional.get();
            File imgFile = new File(propertyImages.getImagePath());
            if (imgFile.exists()) {
                if (!imgFile.delete()) {
                    throw new IOException("Failed to delete image file from file system");
                }
            }
            propertyImagesRepository.delete(propertyImages);
            return "File deleted successfully";
        } else {
            throw new FileNotFoundException("File not found with ID: " + imageId);
        }
    }

    @Override
    public List<PropertyImages> getAllPropertyImages(Long propertyId) {
        return propertyImagesRepository.findByPropertyId(propertyId);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1) {
            throw new IllegalArgumentException("Invalid file name: " + fileName);
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}