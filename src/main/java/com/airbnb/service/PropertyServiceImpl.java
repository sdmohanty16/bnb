package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.entity.Property;
import com.airbnb.exception.PropertyExists;
import com.airbnb.payload.PropertyDto;
import com.airbnb.repository.CityRepository;
import com.airbnb.repository.CountryRepository;
import com.airbnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService{

    private PropertyRepository propertyRepository;
    private CityRepository cityRepository;
    private CountryRepository countryRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.propertyRepository = propertyRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public PropertyDto addProperty(PropertyDto propertyDto,long cityid, long countryid) {
        Optional<City> opCity = cityRepository.findById(cityid);
        City city = opCity.get();
        Optional<Country> opCountry = countryRepository.findById(countryid);
        Country country = opCountry.get();
        Property property = mapToEntity(propertyDto);
        property.setCity(city);
        property.setCountry(country);

        Optional<Property> opName = propertyRepository.findByName(property.getName());
        if(opName.isPresent()){
            throw new PropertyExists("Property already exists");
        }

        Property savedProperty = propertyRepository.save(property);
        PropertyDto dto = mapToDto(savedProperty);
        return dto;
    }


    Property mapToEntity(PropertyDto dto){
        Property entity = new Property();
        entity.setName(dto.getName());
        entity.setNumberOfGuests(dto.getNumberOfGuests());
        entity.setNumberOfBedrooms(dto.getNumberOfBedrooms());
        entity.setNumberOfBeds(dto.getNumberOfBeds());
        entity.setNumberOfBathrooms(dto.getNumberOfBathrooms());
        return entity;
    }

    PropertyDto mapToDto(Property property) {
        PropertyDto dto = new PropertyDto();
        dto.setId(property.getId());
        dto.setName(property.getName());
        dto.setNumberOfGuests(property.getNumberOfGuests());
        dto.setNumberOfBedrooms(property.getNumberOfBedrooms());
        dto.setNumberOfBeds(property.getNumberOfBeds());
        dto.setNumberOfBathrooms(property.getNumberOfBathrooms());
        return dto;
    }
}
