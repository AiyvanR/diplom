package com.example.diploma.Service.impl;

import com.example.diploma.Entity.Image;
import com.example.diploma.Repository.ImageRepository;
import com.example.diploma.Service.ImageService;
import com.example.diploma.imageUtils.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    @Override
    public ResponseEntity<?> get(UUID id) {
        log.info("Fetching image with id {} from the database", id);
        Image image = repository.findById(id).orElseThrow(()-> new RuntimeException("image not found"));
        byte[] imageData = ImageUtils.decompressImage(image.getData());
        return ResponseEntity.ok().contentType(MediaType.valueOf(image.getType()))
                .body(imageData);
    }

    @Override
    public String save(MultipartFile image) {
        log.info("Saving new image to the database");
        Image i = ImageUtils.compressImage(image);
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .build()
                .toUriString() + "/images/" + repository.save(i).getId();
    }


}
