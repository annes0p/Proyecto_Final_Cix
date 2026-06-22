package com.example.cixoil.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.cixoil.dto.CloudinaryUploadResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryUploadResult uploadImageInFolder(MultipartFile file, String folder) {
        if (file.isEmpty()) return null;
        try {
            Map<String, Object> options = new HashMap<>();
            options.put("use_filename", true);
            options.put("unique_filename", true);

            if (folder != null && !folder.isBlank())
                options.put("folder", folder);

            Map<?, ?> res = cloudinary.uploader().upload(file.getBytes(), options);

            return new CloudinaryUploadResult(
                    res.get("public_id").toString(),
                    res.get("secure_url").toString()
            );
        } catch (IOException e) {
            throw new RuntimeException("Error al subir imagen: ", e);
        }
    }

    public void deleteImage(String publicId) {
        try {
            if (publicId == null || publicId.isBlank()) {
                return;
            }

            cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.emptyMap()
            );
        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar imagen: ", e);
        }
    }

    public CloudinaryUploadResult replaceImage(MultipartFile file, String folder, String oldImagePublicId) {
        CloudinaryUploadResult newImage =
                uploadImageInFolder(file, folder);

        if (newImage != null) {
            deleteImage(oldImagePublicId);
        }

        return newImage;
    }
}
