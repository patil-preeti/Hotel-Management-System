package com.application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{
	
	//List<Hotel> findByMaxPersonsGreaterThanEqual(Integer persons);
	
	List<Hotel> findByMaxPersonsGreaterThanEqualAndIsAc(Integer persons, Boolean isAc);
	
	 @Query("""
	           SELECT h
	           FROM Hotel h
	           JOIN h.city c
	           WHERE (:cityName IS NULL OR LOWER(c.cityName) = LOWER(:cityName)) AND
	                 (:persons IS NULL OR h.maxPersons >= :persons)
	           """)
	    List<Hotel> findHotelsByCityAndPersons(@Param("cityName") String cityName,
	                                           @Param("persons") Integer persons);
	 
	
		    @Query("SELECT h.extraImgs FROM Hotel h WHERE h.id = :hotelId")
		    List<String> findExtraImgsByHotelId(Long hotelId);
		

}
