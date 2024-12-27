
package com.application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.City;
import com.application.Hotel;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

//	@Query("SELECT c FROM City c WHERE " 
//			+ "(:cityName IS NULL OR c.cityName = :cityName) AND "
//			+ "(:area IS NULL OR c.area = :area) AND " 
//			+ "(:pincode IS NULL OR c.pincode = :pincode)")
//	
//	 List<City> searchHotels(@Param("cityName") String name, @Param("area") String area,
//		@Param("pincode") String pincode);
	
//	@Query("SELECT c FROM City c " +
//		       "JOIN c.hotels h " + 
//		       "WHERE " +
//		       "(:cityName IS NULL OR c.cityName = :cityName) AND " +
//		       "(:area IS NULL OR c.area = :area) AND " +
//		       "(:pincode IS NULL OR c.pincode = :pincode) AND " +
//		       "(:hotelName IS NULL OR LOWER(h.name) = LOWER(:hotelName))")
//		List<City> searchHotels(@Param("cityName") String cityName,
//		                        @Param("area") String area,
//		                        @Param("pincode") String pincode,
//		                        @Param("hotelName") String hotelName);
	
	
	boolean existsByCityName(String cityName);

	@Query("SELECT c FROM City c WHERE " +
	"(:cityName is NULL OR c.cityName = :cityName) AND " +
	"(:area is NULL OR c.area = :area) AND " +
	"(:pincode is NULL OR c.pincode = :pincode)")
	List< City> searchHotels(@Param("cityName") String cityName,
	@Param("area") String area,
	@Param("pincode") String pincode);
	
	
	
	@Query("SELECT c FROM City c " +
		       "JOIN c.hotels h " +
		       "WHERE " +
		       "(c.cityName LIKE CONCAT('%', :destination, '%') OR " +
		       "c.area LIKE CONCAT('%', :destination, '%') OR " +
		       "c.pincode LIKE CONCAT('%', :destination, '%') OR " +
		       "h.name LIKE CONCAT('%', :destination, '%')) AND " +
		       "(:maxPersons IS NULL OR h.maxPersons = :maxPersons)")
		List<City> findByDestination(@Param("destination") String destination, @Param("maxPersons") Integer maxPersons);
}