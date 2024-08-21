package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import com.airbnb.exception.DescriptionExists;
import com.airbnb.exception.PropertyExists;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.PropertyDto;
import com.airbnb.payload.ReviewDto;
import com.airbnb.repository.AppUserRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepository reviewRepository;
    private AppUserRepository appUserRepository;
    private PropertyRepository propertyRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, AppUserRepository appUserRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.appUserRepository = appUserRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public ReviewDto addReview(ReviewDto reviewDto, AppUser user, long propertyId) {

        Optional<Property> opId = propertyRepository.findById(propertyId);
        Property property = opId.get();
        Review review = mapToEntity(reviewDto);
        review.setProperty(property);
        review.setAppUser(user);

        Optional<Review> opDescription = reviewRepository.findByDescription(review.getDescription());
        if(opDescription.isPresent()){
            throw new DescriptionExists("Description already exists...");
        }

        Review savedReview = reviewRepository.save(review);
        ReviewDto dto = mapToDto(savedReview);
        return dto;
    }



    Review mapToEntity(ReviewDto dto){
        Review entity = new Review();
        entity.setRating(dto.getRating());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    ReviewDto mapToDto(Review review){
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setDescription(review.getDescription());
        return dto;
    }


}
