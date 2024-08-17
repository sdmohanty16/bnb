package com.airbnb.repository;

import com.airbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> findByName(String name);

    @Query("select p from Property p JOIN City c on p.city=c.id where c.name=:cityName")
    List<Property> searchProperty(
        @Param("cityName") String cityName
    );
}