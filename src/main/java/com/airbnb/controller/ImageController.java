package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Image;
import com.airbnb.entity.Property;
import com.airbnb.repository.ImageRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.service.BucketService;
import com.airbnb.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {


    private BucketService bucketService;
    private ImageService imageService;
    private PropertyRepository propertyRepository;

    public ImageController(BucketService bucketService, ImageService imageService, PropertyRepository propertyRepository) {
        this.bucketService = bucketService;
        this.imageService = imageService;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping(path = "/upload/file/{bucketName}/property/{propertyId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Image> uploadImage(
            @RequestParam MultipartFile file,
            @PathVariable String bucketName,
            @PathVariable long propertyId,
            @AuthenticationPrincipal AppUser user
            ){

        String imageUrl = bucketService.uploadFile(file, bucketName);
        Property property = propertyRepository.findById(propertyId).get();

        Image img = new Image();
        img.setProperty(property);
        img.setUrl(imageUrl);

        Image savedImage = imageService.createImage(img);
        return new ResponseEntity<>(savedImage, HttpStatus.OK);
    }


}
