package com.lucky.scm.services;

public interface OtpService {
    

    String sendOtpToPhone(String mobileNumber);

    String vailidatedOtp(String otp);

}
