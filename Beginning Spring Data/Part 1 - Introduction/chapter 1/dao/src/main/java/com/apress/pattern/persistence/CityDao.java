package com.apress.pattern.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.apress.pattern.model.City;

public class CityDao implements ICommonDao<City> {

	private List<City> cities = new ArrayList<City>();
	
    public CityDao() {
    	cities.add(new City("BUE", "Buenos Aires"));
    	cities.add(new City("SCL", "Santiago de Chile"));
    }

	public Optional<City> get(long id) {
		return Optional.ofNullable(cities.get((int) id));
	}

	public List<City> getAll() {
		return cities;
	}

	public void save(City city) {
		cities.add(city);
		
	}

	public void delete(City city) {
		cities.remove(city);
	}
}
