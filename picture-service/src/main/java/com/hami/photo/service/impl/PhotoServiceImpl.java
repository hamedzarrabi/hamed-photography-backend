package com.hami.photo.service.impl;

import com.hami.photo.common.entity.category.Category;
import com.hami.photo.common.entity.photos.Photo;
import com.hami.photo.common.exception.ResourceNotFoundException;
import com.hami.photo.dto.CategoryDto;
import com.hami.photo.dto.PhotoDto;
import com.hami.photo.repository.CategoryRepository;
import com.hami.photo.repository.PhotoRepository;
import com.hami.photo.service.CategoryService;
import com.hami.photo.service.PhotoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired private PhotoRepository photoRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private ModelMapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoServiceImpl.class);

    @Override
    public PhotoDto createPhoto(PhotoDto photoDto) {
        Photo photo = mapToEntity(photoDto);

        Category byId = categoryRepository.findById(photoDto.getCategory()).get();

        photo.setCategory(byId);

        Photo savePhoto = photoRepository.save(photo);
        return mapToDto(savePhoto);
    }

    @Override
    public PhotoDto updatePhoto(UUID photoId, PhotoDto photoDto) {
        if (photoRepository.findById(photoId).isPresent()) {
            Photo photo = mapToEntity(photoDto);
            photo.setPhoto_id(photoId);
            Photo updatePhoto = photoRepository.save(photo);
            return mapToDto(updatePhoto);

        } else {
            return null;
        }
    }

    @Override
    public List<PhotoDto> findAllPhotos() {
        List<Photo> allPhoto = photoRepository.findAll();
        return allPhoto.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PhotoDto findByCategory(UUID categoryId) {
        return null;
    }

    @Override
    public PhotoDto findByPhotoId(UUID photoId) {
        Photo byId = photoRepository.findById(photoId).orElseThrow(() -> new ResourceNotFoundException("Photo", "id", photoId));
        return mapToDto(byId);
    }

    @Override
    public void deletePhoto(UUID photoId) {
        Photo photo = photoRepository.findById(photoId).orElseThrow(
                () -> new ResourceNotFoundException("Photo ", "id", photoId)
        );

        photoRepository.delete(photo);
    }

    private PhotoDto mapToDto(Photo photo) {
        PhotoDto productDto = mapper.map(photo, PhotoDto.class);
        return productDto;
    }

    private Photo mapToEntity(PhotoDto photoDto) {
        Photo photo = mapper.map(photoDto, Photo.class);
        return photo;
    }

    public static void cleanDir(String dir) {
        Path dirPath = Paths.get(dir);

        try {
            Files.walk(dirPath)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            LOGGER.error("Could not delete files in directory: " + dirPath, e);
        }
    }

}
