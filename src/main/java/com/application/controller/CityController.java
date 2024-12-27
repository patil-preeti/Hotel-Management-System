package com.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.City;
import com.application.Hotel;
import com.application.exception.DuplicateException;
import com.application.exception.ResourceNotFoundException;
import com.application.repo.CityRepository;
import com.application.repo.HotelRepository;
import com.application.service.CityService;
import com.application.service.HotelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/city")
public class CityController {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private CityService cityService;

	@GetMapping("/searchforAc")
	public List<Hotel> getHotelsByPersonsAndAc(@RequestParam Integer persons, @RequestParam Boolean isAc) {
		return hotelService.findHotelsByPersonsAndAc(persons, isAc);
	}

//	 @GetMapping("/search-by-persons")
//	    public List<Hotel> getHotelsByPersons(@RequestParam Integer persons) {
//	        return hotelService.findHotelsByPersons(persons);
//	    }
//	 
//	 @GetMapping("/search")
//	 public List<City> searchHotels(
//	         @RequestParam(name = "name", required = false) String hotelName,
//	         @RequestParam(name = "cityName", required = false) String cityName,
//	         @RequestParam(name = "area", required = false) String area,
//	         @RequestParam(name = "pincode", required = false) String pincode) {
//	     return cityRepository.searchHotels(cityName, area, pincode, hotelName);
//	 }

	@GetMapping("/search")
	public ResponseEntity<List<City>> searchCities(@RequestParam String destination, @RequestParam Integer maxPersons) {
	    System.out.println("Controller method invoked with destination: " + destination + " and maxPersons: " + maxPersons);

	    
	    List<City> citiesByDestination = cityService.searchCitiesByDestination(destination);

	
	    if (citiesByDestination.isEmpty()) {
	        throw new ResourceNotFoundException("No data found matching with: " + destination);
	    }

	   
	    List<City> citiesByMaxPersons = cityService.searchCities(destination, maxPersons);

	    
	    if (citiesByMaxPersons.isEmpty()) {
	        throw new ResourceNotFoundException("No hotels found with the specified maxPersons: " 
	                + maxPersons + " for destination: " + destination);
	    }

	    return ResponseEntity.ok(citiesByMaxPersons);
	}

	
	@GetMapping("/searchByCityAndPersons")
	public List<Hotel> getHotelsByCityAndPersons(@RequestParam(name = "cityName", required = false) String cityName,
			@RequestParam(name = "persons", required = false) Integer persons) {
		return hotelService.findHotelsByCityAndPersons(cityName, persons);
	}

	@PostMapping("/add")
	public ResponseEntity<City> addHotelToCity(@RequestParam Long cityId, @RequestBody Hotel hotel) {
	    
	    City city = cityRepository.findById(cityId)
	            .orElseThrow(() -> new RuntimeException("City with ID " + cityId + " not found."));

	   
	    boolean hotelExists = city.getHotels().stream()
	            .anyMatch(existingHotel -> existingHotel.getName().equalsIgnoreCase(hotel.getName()));

	    if (hotelExists) {
	        throw new DuplicateException("Hotel with name '" + hotel.getName() + "' already exists in city '" + city.getCityName() + "'.");
	    }

	    
	    hotel.setCity(city);
	    hotelRepository.save(hotel);

	    return ResponseEntity.ok(city);
	}

}
