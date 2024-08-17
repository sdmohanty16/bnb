package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.exception.CityExists;
import com.airbnb.payload.CityDto;
import com.airbnb.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService{

    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public CityDto addCity(CityDto cityDto) {
        City city = mapToEntity(cityDto);

        Optional<City> opName = cityRepository.findByName(city.getName());
        if(opName.isPresent()){
            throw new CityExists("City already exists");
        }
        City savedCity = cityRepository.save(city);
        CityDto dto = mapToDto(savedCity);
        return dto;
    }

    @Override
    public void deleteCity(long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public CityDto updateCity(long id, CityDto cityDto) {
        Optional<City> opId = cityRepository.findById(id);
        City city = opId.get();
        city.setName(cityDto.getName());

        City savedCity = cityRepository.save(city);
        CityDto dto = mapToDto(savedCity);
        return dto;
    }

    @Override
    public List<CityDto> getCity() {
        List<City> cities = cityRepository.findAll();
        List<CityDto> collect = cities.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return collect;
    }

    City mapToEntity(CityDto dto){
        City entity = new City();
        entity.setName(dto.getName());
        return entity;
    }

    CityDto mapToDto(City city){
        CityDto dto = new CityDto();
        dto.setId(city.getId());
        dto.setName(city.getName());
        return dto;
    }
}
