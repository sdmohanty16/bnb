package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Review;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.ReviewDto;

public interface ReviewService {

    ReviewDto addReview(ReviewDto reviewDto, AppUser user, long propertyId);
}
