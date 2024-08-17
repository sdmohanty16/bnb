package com.airbnb.service;

import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;

import java.util.List;

public interface PropertyService {

    PropertyDto addProperty(PropertyDto propertyDto, long cityid, long countryid);

}
