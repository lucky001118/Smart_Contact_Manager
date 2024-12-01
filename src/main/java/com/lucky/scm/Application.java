package com.lucky.scm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucky.scm.config.OtpTwilioConfig;
import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Application {
	@Autowired
	private OtpTwilioConfig otpTwilioConfig;

	@PostConstruct
	public void setup(){
		Twilio.init(otpTwilioConfig.getAccountSID(), otpTwilioConfig.getAuthToken());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
