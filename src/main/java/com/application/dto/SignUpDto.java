package com.application.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SignUpDto {
		
		@Id
		private Long id;
		private String fullName;
		private String dOB;
		private String email;
		private String mobileNo;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getdOB() {
			return dOB;
		}
		public void setdOB(String dOB) {
			this.dOB = dOB;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}
		
		
		public SignUpDto(Long Id, String fullName, String dOB, String email,String mobileNo) {
			super();
			this.id = id;
			this.fullName = fullName;
			this.dOB = dOB;
			this.email = email ;
			this.mobileNo = mobileNo;
		}
		
		public SignUpDto ()
		{
			super();
		}

}
