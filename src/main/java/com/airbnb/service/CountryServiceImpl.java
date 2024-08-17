package com.airbnb.service;

import com.airbnb.entity.Country;
import com.airbnb.exception.CountryExists;
import com.airbnb.payload.CountryDto;
import com.airbnb.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService{

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public CountryDto addCountry(CountryDto countryDto) {
        Country country = mapToEntity(countryDto);

        Optional<Country> opName = countryRepository.findByName(country.getName());
        if(opName.isPresent()){
            throw new CountryExists("Country already exists");
        }

        Country savedCountry = countryRepository.save(country);
        CountryDto dto = mapTODto(savedCountry);
        return dto;
    }

    @Override
    public void deleteCountry(long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public CountryDto updateCountry(long id, CountryDto countryDto) {
        Optional<Country> cntry = countryRepository.findById(id);
        Country country = cntry.get();
        country.setName(countryDto.getName());

        Country savedCountry = countryRepository.save(country);
        CountryDto dto = mapTODto(savedCountry);
        return dto;

    }

    @Override
    public List<CountryDto> getCountry() {
        List<Country> countries = countryRepository.findAll();
        List<CountryDto> countryDtos = countries.stream()
                .map(c -> mapTODto(c))
                .collect(Collectors.toList());
        return countryDtos;
    }


    Country mapToEntity(CountryDto dto){
        Country entity = new Country();
        entity.setName(dto.getName());
        return entity;
    }

    CountryDto mapTODto(Country country){
        CountryDto dto = new CountryDto();
        dto.setId(country.getId());
        dto.setName(country.getName());
        return dto;
    }

}
