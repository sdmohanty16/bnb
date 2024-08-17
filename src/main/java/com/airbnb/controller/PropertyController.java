package com.airbnb.controller;

import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;
import com.airbnb.service.CityService;
import com.airbnb.service.CountryService;
import com.airbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private PropertyService propertyService;


    public PropertyController(PropertyService propertyService, CountryService countryService, CityService cityService) {
        this.propertyService = propertyService;

    }

    //http://localhost:8080/api/v1/property/add
    @PostMapping("/add")
    public ResponseEntity<PropertyDto> addProperty(
            @RequestParam long cityid,
            @RequestParam long countryid,
            @RequestBody PropertyDto propertyDto
    ){
        PropertyDto addProperty = propertyService.addProperty(propertyDto,cityid,countryid);
        return new ResponseEntity<>(addProperty, HttpStatus.CREATED);
    }



}
