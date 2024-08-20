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


    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;

    }

    //http://localhost:8080/api/v1/property/add?cityid= &countryid=
    @PostMapping("/add")
    public ResponseEntity<PropertyDto> addProperty(
            @RequestParam long cityid,
            @RequestParam long countryid,
            @RequestBody PropertyDto propertyDto
    ){
        PropertyDto addProperty = propertyService.addProperty(propertyDto,cityid,countryid);
        return new ResponseEntity<>(addProperty, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/property/propertyresult?city=
    @GetMapping("/propertyresult")
    public List<Property> searchProperty(
            @RequestParam("city") String cityName
    ){
        return propertyService.searchProperty(cityName);
    }

    //http://localhost:8080/api/v1/property/delete?id=
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProperty(
            @RequestParam long id
    ){
        propertyService.deleteProperty(id);
        return new ResponseEntity<>("Property deleted successfully..." , HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/property/update?id=
    @PutMapping("/update")
    public ResponseEntity<PropertyDto> updateProperty(
            @RequestParam long id,
            @RequestBody PropertyDto propertyDto
    ){
        PropertyDto dto = propertyService.updateProperty(id, propertyDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/property/getallproperty
    @GetMapping("/getallproperty")
    public ResponseEntity <List<PropertyDto>> getAllProperty(){
        List<PropertyDto> allProperty = propertyService.getAllProperty();
        return new ResponseEntity<>(allProperty, HttpStatus.OK);
    }

}
