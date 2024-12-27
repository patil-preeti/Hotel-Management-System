package com.application;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotels")
public class Hotel {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String description;
	    private String imageUrl;
	
	    private String address;
	    private Double pricePerNight;
		private Integer maxPersons;
		private String bedrooms;
		private String beds;
		private String bathrooms;
		private Boolean isAc;
		private String rating;

		@ElementCollection
		private List<String> extraImgs ;

		@ManyToOne
	    @JoinColumn(name = "city_id")
	    @JsonBackReference
	    private City city;

	    public void setId(Long id) { this.id = id; }
	    public Long getId() { return id; }
	    public void setName(String name) { this.name = name; }
	    public String getName() { return name; }
	    public void setDescription(String description) {this.description = description;}
	    public String getDescription() {return description;}
	    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}
		public String getImageUrl() {return imageUrl;}
		public List<String> getExtraImgs() {return extraImgs;}
		public void setExtraImgs(List<String> extraImgs) {this.extraImgs = extraImgs;}
		public void setAddress(String address) { this.address = address; }
	    public String getAddress() { return address; }
		public void setPricePerNight(Double pricePerNight) {this.pricePerNight = pricePerNight;}
		public Double getPricePerNight() {return pricePerNight;}
		public Integer getMaxPersons() {return maxPersons;}
		public void setMaxPersons(Integer maxPersons) {this.maxPersons = maxPersons;}
		public String getBedrooms() {return bedrooms;}
		public void setBedrooms(String bedrooms) {this.bedrooms = bedrooms;}
		public String getBeds() {return beds;}
		public void setBeds(String beds) {this.beds = beds;}
		public String getBathrooms() {return bathrooms;}
		public void setBathrooms(String bathrooms) {this.bathrooms = bathrooms;}
		public Boolean getIsAc() {return isAc;}
		public void setIsAc(Boolean isAc) {this.isAc = isAc;}
		public String getRating() {return rating;}
		public void setRating(String rating) {this.rating = rating;}
	    public void setCity(City city) { this.city = city; }
	    public City getCity() { return city; }
	    
	  
}
