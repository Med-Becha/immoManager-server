package com.asm.immoManager.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.asm.immoManager.entity.PropertyImages;

public interface PropertyImagesService {

    public String uploadPropertyImages(Long propertyId, MultipartFile file) throws IOException;

    public byte[] downloadPropertyImages(String images) throws IOException;

    public String deletePropertyImage(Long imageId) throws IOException;

    List<PropertyImages> getAllPropertyImages(Long propertyId);

}
