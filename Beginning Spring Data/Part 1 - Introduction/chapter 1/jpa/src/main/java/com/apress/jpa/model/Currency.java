package com.apress.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity //Required
@Table(name = "currency") //Optional only if you need to indicate the name
public class Currency implements Serializable {
	
	@Id //Required - Identify which is the primary key
	@GeneratedValue(strategy = GenerationType.SEQUENCE) //Indicate the way to generate the ID
	private Long id;
	
	private String code;
	private String description;
	private Boolean enable;
	
	@Column(name = "decimal_places", length = 5) //Optional: Indicate the name and the lenght of the column 
	private String decimalPlaces;
	
	public Currency(Long id, String code, String description, Boolean enable, String decimalPlaces) {
		this.id = id;
		this.code = code;
		this.description = description;
		this.enable = enable;
		this.decimalPlaces = decimalPlaces;
	}

	public Currency() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getEnable() {
		return enable;
	}
	
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	public String getDecimalPlaces() {
		return decimalPlaces;
	}
	
	public void setDecimalPlaces(String decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Currency currency = (Currency) o;
		return Objects.equals(id, currency.id) && Objects.equals(code, currency.code) && Objects.equals(description, currency.description) && Objects.equals(enable, currency.enable) && Objects.equals(decimalPlaces, currency.decimalPlaces);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, code, description, enable, decimalPlaces);
	}
}
