package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Review;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.ReviewDto;
import com.airbnb.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //http://localhost:8080/api/v1/review/add
    @PostMapping("/add")
    public ResponseEntity<ReviewDto> addReview(
            @RequestBody ReviewDto reviewDto,
            @AuthenticationPrincipal AppUser user,
            @RequestParam long propertyId
    ){
        ReviewDto addReview = reviewService.addReview(reviewDto, user, propertyId);
        return new ResponseEntity<>(addReview, HttpStatus.CREATED);
    }
}
