package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import com.airbnb.exception.ReviewExists;
import com.airbnb.payload.ReviewDto;
import com.airbnb.repository.AppUserRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Review reviewDetails = reviewRepository.findByUserAndProperty(user, property);
        if(reviewDetails!=null){
            throw new ReviewExists("Review already exists...");
        }
        Review review = mapToEntity(reviewDto);
        review.setProperty(property);
        review.setAppUser(user);

        Review savedReview = reviewRepository.save(review);
        ReviewDto dto = mapToDto(savedReview);
        return dto;
    }

    @Override
    public List<ReviewDto> listReviewsOfUser(AppUser user) {
        List<Review> reviews = reviewRepository.findReviewsByUser(user);
        List<ReviewDto> collected = reviews.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return collected;
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
