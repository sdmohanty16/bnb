package com.airbnb.repository;

import com.airbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> findByName(String name);

    @Query("select p from Property p JOIN p.city c JOIN p.country co where c.name=:name or co.name=:name")
    List<Property> searchProperty(@Param("name") String name);


    @Modifying
    @Query("DELETE from Property p where p.city.id = :id")
    int deletePropertyByCityId(@Param("id") Long id);


}