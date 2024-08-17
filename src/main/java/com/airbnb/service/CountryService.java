package com.airbnb.service;

import com.airbnb.entity.Country;
import com.airbnb.payload.CountryDto;

import java.util.List;

public interface CountryService {

    CountryDto addCountry(CountryDto countryDto);

    void deleteCountry(long id);


    CountryDto updateCountry(long id, CountryDto countryDto);


    List<CountryDto> getCountry();
}
