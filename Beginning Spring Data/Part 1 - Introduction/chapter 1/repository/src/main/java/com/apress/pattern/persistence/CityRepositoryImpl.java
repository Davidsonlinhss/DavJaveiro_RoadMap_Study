package com.apress.pattern.persistence;

import java.util.List;
import java.util.Optional;

import com.apress.pattern.model.City;

public class CityRepositoryImpl implements CityRepository {

    private CityDao cityDao;
    
    public CityRepositoryImpl(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	public Optional<City> get(long id) {
        return cityDao.get(id);
    }
    
    public List<City> getAll() {
    	return cityDao.getAll();
    }
    
    public void save(City city) {
    	cityDao.save(city);
    }
    
    public void delete(City city) {
    	cityDao.delete(city);
    }
}
