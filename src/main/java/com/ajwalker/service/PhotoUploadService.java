package com.ajwalker.service;

import com.ajwalker.entity.Photo;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.repository.PhotoRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PhotoUploadService {
    private final Cloudinary cloudinary;
    private final PhotoRepository photoRepository;


    public String uploadPhoto(MultipartFile file) throws IOException {
        final long MAX_FILE_SIZE = 5 * 1024 * 1024;

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new TierdYolException(ErrorType.PHOTO_SIZE_ERROR);
        }

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("url").toString();
    }

    public void save(String url, Long id) {
        Photo photo = Photo.builder()
                .url(url)
                .productId(id)
                .build();
        photoRepository.save(photo);
    }
}
