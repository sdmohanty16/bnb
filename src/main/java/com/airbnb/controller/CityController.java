package com.airbnb.controller;

import com.airbnb.entity.City;
import com.airbnb.payload.CityDto;
import com.airbnb.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    //http://localhost:8080/api/v1/city/add
    @PostMapping("/add")
    public ResponseEntity<CityDto> addCity(
            @RequestBody CityDto cityDto
    ){
        CityDto addCity = cityService.addCity(cityDto);
        return new ResponseEntity<>(addCity, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/city/delete?id=
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCity(
        @RequestParam long id
    ){
        cityService.deleteCity(id);
        return new ResponseEntity<>("City deleted successfully...", HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/city/update?id=
    @PutMapping("/update")
    public ResponseEntity<CityDto> updateCity(
            @RequestParam long id,
            @RequestBody CityDto cityDto
    ){
        CityDto city = cityService.updateCity(id, cityDto);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/city/getcity
    @GetMapping("/getcity")
    public ResponseEntity<List<CityDto>> getCity(){
        List<CityDto> cityDtos = cityService.getCity();
        return new ResponseEntity<>(cityDtos, HttpStatus.OK);
    }
}
