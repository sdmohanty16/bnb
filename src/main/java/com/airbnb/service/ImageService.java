package com.airbnb.service;

import com.airbnb.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Image createImage(Image img);
}
