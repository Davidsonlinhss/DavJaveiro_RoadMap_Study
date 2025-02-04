package com.apress.pattern.model;

import java.util.Objects;

public class City {
	
	private String code;
	private String name;
	
	public City(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		City city = (City) o;
		return Objects.equals(code, city.code) && Objects.equals(name, city.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, name);
	}
}