package com.airbnb.service;

import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;

import java.util.List;

public interface PropertyService {

    PropertyDto addProperty(PropertyDto propertyDto, long cityid, long countryid);

    public List<Property> searchProperty(String cityName);

    void deleteProperty(long id);


    PropertyDto updateProperty(long id, PropertyDto propertyDto);


    List<PropertyDto> getAllProperty();
}
