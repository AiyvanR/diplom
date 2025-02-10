package com.example.diploma.Service;

import com.example.diploma.Entity.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImageService {
    ResponseEntity<?> get(UUID id);
    String save(MultipartFile image);

}
