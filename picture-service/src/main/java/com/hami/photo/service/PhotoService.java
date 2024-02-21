package com.hami.photo.service;

import com.hami.photo.dto.PhotoDto;

import java.util.List;
import java.util.UUID;

public interface PhotoService {
    PhotoDto createPhoto(PhotoDto photoDto);
    PhotoDto updatePhoto(UUID photoId, PhotoDto photoDto);
    List<PhotoDto> findAllPhotos();
    PhotoDto findByCategory(UUID categoryId);
    PhotoDto findByPhotoId(UUID photoId);
    void deletePhoto(UUID photoId);
}
