package com.hami.photo.controller;

import com.hami.photo.dto.CategoryDto;
import com.hami.photo.dto.PhotoDto;
import com.hami.photo.service.CategoryService;
import com.hami.photo.service.PhotoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.hami.photo.service.impl.PhotoServiceImpl.cleanDir;


@Tag(name = "Photo Controller", description = "hamed photo.")
@RestController
@RequestMapping("/api/v1/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "Create Photo REST API")
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PhotoDto> createPhoto(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("imageFile") MultipartFile image,
            @RequestParam("category_id") UUID categoryId

    ) {

        PhotoDto photoDto = new PhotoDto();

        String imageAddress = "images/photos/" + title;
        String originalAddressImage = "D:/images/" + title;

        insertPhoto(title, description, image, categoryId, photoDto, imageAddress, originalAddressImage);

        PhotoDto photo = photoService.createPhoto(photoDto);

        return new ResponseEntity<>(photo, HttpStatus.CREATED);
    }

    @ApiOperation(value = "update Photo REST API")
    @PutMapping(value = "/update/{photo_id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePhoto(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("imageFile") MultipartFile image,
            @RequestParam("category_id") UUID categoryId,
            @PathVariable("photo_id") UUID photoId

    ) {

        PhotoDto byPhotoId = photoService.findByPhotoId(photoId);

        if (byPhotoId.getPhoto_id() != null) {

            PhotoDto photoDto = new PhotoDto();

            String imageAddress = "images/photos/" + title;
            String originalAddressImage = "D:/images/" + title;

            insertPhoto(title, description, image, categoryId, photoDto, imageAddress, originalAddressImage);

            PhotoDto photo = photoService.updatePhoto(byPhotoId.getPhoto_id(), photoDto);

            return new ResponseEntity<>(photo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("photo not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{photo_id}")
    public ResponseEntity<String> deletePhoto(@PathVariable(name = "photo_id") UUID photoId) {
        PhotoDto byPhotoId = photoService.findByPhotoId(photoId);
        System.err.println(byPhotoId.getAddress());
        cleanDir(byPhotoId.getAddress());
        photoService.deletePhoto(photoId);
        return new ResponseEntity<>("Deleted Successfully!", HttpStatus.OK);
    }

    @GetMapping("/{photo_id}")
    public ResponseEntity<PhotoDto> findPhotoById(@PathVariable("photo_id") UUID photoId) {
        PhotoDto byPhotoId = photoService.findByPhotoId(photoId);
        return new ResponseEntity<>(byPhotoId, HttpStatus.OK);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<PhotoDto>> findAllPhotos() {
        List<PhotoDto> allPhotos = photoService.findAllPhotos();
        return new ResponseEntity<>(allPhotos, HttpStatus.OK);
    }


    private void insertPhoto(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("imageFile") MultipartFile image,
            @RequestParam("category_id") UUID categoryId,
            PhotoDto photoDto,
            String imageAddress,
            String originalAddressImage) {


        System.err.println("Original Address: " + originalAddressImage);

        photoDto.setPhoto_id(UUID.randomUUID());
        photoDto.setTitle(title);
        photoDto.setImage(imageAddress + "/" + image.getOriginalFilename());
        photoDto.setAddress(originalAddressImage + "/" + image.getOriginalFilename());

        CategoryDto categoryById = categoryService.findCategoryById(categoryId);

        photoDto.setCategory(categoryById.getCategoryId());
        photoDto.setDescription(description);

        savePicture(image, originalAddressImage);
    }


    private void savePicture(MultipartFile image, String address) {
        saveImage(image, address);
    }

    public static void saveImage(MultipartFile image, String address) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        try {
            Path path = Paths.get(address);
            Files.createDirectories(path);
            Files.copy(image.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

}
