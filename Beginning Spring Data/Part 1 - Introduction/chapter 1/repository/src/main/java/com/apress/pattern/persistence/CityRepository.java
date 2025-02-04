package com.apress.pattern.persistence;

import java.util.List;
import java.util.Optional;

import com.apress.pattern.model.City;

interface CityRepository {
    
    Optional<City> get(long id);
    
    List<City> getAll();
    
    void save(City city);
    
    void delete(City city);
}
