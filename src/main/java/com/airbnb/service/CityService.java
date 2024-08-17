package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.payload.CityDto;

import java.util.List;

public interface CityService {

    CityDto addCity(CityDto cityDto);

    void deleteCity(long id);

    CityDto updateCity(long id, CityDto cityDto);

    List<CityDto> getCity();
}
