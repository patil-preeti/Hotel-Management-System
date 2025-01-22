package com.application.dto;

public class SignInDto {
	 private String mobileNo;
	    private String otp;
	
	    public SignInDto(Long Id, String fullName, String dOB, String email,String mobileNo, String otp) 
		{
			this.mobileNo = mobileNo;
			this.otp = otp;
		}
		
		public SignInDto ()
		{
			
		} 

}
