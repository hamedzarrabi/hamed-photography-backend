package com.hami.photo.mapper;

import com.hami.photo.common.entity.photos.Photo;
import com.hami.photo.dto.PhotoDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhotoMapper {
//    @Autowired
//    private ModelMapper mapper;
//    public static PhotoDto mapToDto(Photo photo) {
//        return new PhotoDto(
//                photo.getPhoto_id(),
//                photo.getTitle(),
//                photo.getDescription(),
//                photo.getImage(),
//                photo.getCategory().getCategoryId()
//        );
//    }
//
//    public static Photo mapToEntity(PhotoDto photoDto) {
//
//    }
}
