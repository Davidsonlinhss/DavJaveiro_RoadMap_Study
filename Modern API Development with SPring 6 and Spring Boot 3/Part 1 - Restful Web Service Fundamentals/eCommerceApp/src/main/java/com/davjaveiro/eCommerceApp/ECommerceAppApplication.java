package com.davjaveiro.eCommerceApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(ECommerceAppApplication.class, args);

		Number[] newArray = new Integer[2];
		newArray[0] = Double.valueOf(10.2);
	}

}
