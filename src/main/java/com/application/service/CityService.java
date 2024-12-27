package com.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.City;
import com.application.Hotel;
import com.application.repo.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

//	    public List<City> searchCities(String destination, Integer maxPersons) {
//	        return cityRepository.findByDestination(destination, maxPersons);
//	    }

	    
	    public List<City> searchCitiesByDestination(String destination) {
	        return cityRepository.findByDestination(destination, null); // Pass null for maxPersons
	    }

	    public List<City> searchCities(String destination, Integer maxPersons) {
	        return cityRepository.findByDestination(destination, maxPersons);
	    }

	
	
}
