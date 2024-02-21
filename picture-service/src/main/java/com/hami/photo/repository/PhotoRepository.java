package com.hami.photo.repository;

import com.hami.photo.common.entity.category.Category;
import com.hami.photo.common.entity.photos.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface PhotoRepository extends JpaRepository<Photo, UUID> {
}
