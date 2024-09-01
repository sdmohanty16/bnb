package com.airbnb.service;

import com.airbnb.entity.Image;
import com.airbnb.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService{

    private ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @Override
    public Image createImage(Image img) {
        Image savedImage = imageRepository.save(img);
        return savedImage;
    }
}
