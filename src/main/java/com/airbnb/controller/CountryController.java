package com.airbnb.controller;

import com.airbnb.entity.Country;
import com.airbnb.payload.CountryDto;
import com.airbnb.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    //http://localhost:8080/api/v1/country/add
    @PostMapping("/add")
    public ResponseEntity<CountryDto> addCountry(
            @RequestBody CountryDto countryDto
    ){
        CountryDto addCountry = countryService.addCountry(countryDto);
        return new ResponseEntity<>(addCountry, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/country/delete?id=
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCountry(
            @RequestParam long id
    ){
        countryService.deleteCountry(id);
        return new ResponseEntity<>("Country is deleted", HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/country/update?id=
    @PutMapping("/update")
    public ResponseEntity<CountryDto> updateCountry(
            @RequestBody CountryDto countryDto,
            @RequestParam long id
    ){
        CountryDto dto = countryService.updateCountry(id, countryDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    //http://localhost:8080/api/v1/country/getcountry
    @GetMapping("/getcountry")
    public ResponseEntity<List<CountryDto>> getCountry() {
        List<CountryDto> country = countryService.getCountry();
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

}
