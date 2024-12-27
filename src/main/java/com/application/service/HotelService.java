package com.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Hotel;
import com.application.repo.HotelRepository;
@Service
public class HotelService {
	 @Autowired
	    private HotelRepository hotelRepository;

//	    public List<Hotel> findHotelsByPersons(Integer persons) {
//	        return hotelRepository.findByMaxPersonsGreaterThanEqual(persons);
//	    }	
	 
	
	    public List<Hotel> findHotelsByCityAndPersons(String cityName, Integer persons) {
	        return hotelRepository.findHotelsByCityAndPersons(cityName, persons);
	    }
	    
	    public List<Hotel> findHotelsByPersonsAndAc(Integer persons, Boolean isAc) {
	        return hotelRepository.findByMaxPersonsGreaterThanEqualAndIsAc(persons, isAc);
	    }
	    
	    
	    public List<String> getImageUrlsByHotelId(Long hotelId) {
	        return hotelRepository.findExtraImgsByHotelId(hotelId);
	    }
	  
	
}
