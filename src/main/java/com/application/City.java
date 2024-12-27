package com.application;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="cities")
public class City {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String cityName;
	    private String area;
	    private String pincode; 
	    
	    
	    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonManagedReference
	   
	    private List<Hotel> hotels = new ArrayList<>();

	    // Getters and Setters	
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getCityName() { return cityName; }
	    public void setCityName(String cityName) { this.cityName = cityName; }
	    public String getArea() { return area; }
	    public void setArea(String area) { this.area = area; }
	    public String getPincode() { return pincode; }
	    public void setPincode(String pincode) { this.pincode = pincode; }
	    public List<Hotel> getHotels() { return hotels; }
	    public void setHotels(List<Hotel> hotels) { this.hotels = hotels; }
	


}
