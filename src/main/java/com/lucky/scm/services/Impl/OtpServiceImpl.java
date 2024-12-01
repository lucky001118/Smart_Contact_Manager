package com.lucky.scm.services.Impl;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lucky.scm.config.OtpTwilioConfig;
import com.lucky.scm.services.OtpService;

@Service
public class OtpServiceImpl implements OtpService{
    
    @Autowired
    private OtpTwilioConfig otpTwilioConfig;

    Map<String,String> otpMap = new HashMap<String,String>();

    //Fisrtally generate otp
    public String genratedOtp(){
        int otp = (int)(Math.random()*1000000);
        return String.format("%06d",otp);
    }

    //sending the otp
    public String sendOtpToPhone(String mobileNumber){
        String otp = genratedOtp();
        System.out.println(otp);
        PhoneNumber reciepientPhoneNumber = new PhoneNumber("+91 72476 57122");
        PhoneNumber senderPhoneNumber = new PhoneNumber(otpTwilioConfig.getPhoneNumber());
        String msgBody = "Dear Customer,\r\n" + //
                        "Thank you for choosing Smart Camtact Manager's message service. Use the following OTP to complete the procedure to validate your number. OTP is valid for 5 minutes. Do not share this code with others "+otp;
        System.out.println(msgBody);
        Message message = Message.creator(reciepientPhoneNumber,senderPhoneNumber,msgBody).create();
        otpMap.put(msgBody,otp);
        return otp;
    }

    //validate the otp
    public String vailidatedOtp(String otp){
        Set<String> keys=otpMap.keySet();
        String value = null;
        for(String key:keys)
            value=otpMap.get(key);
        if(value.equals(otp))
            return "Otp validated";
        else
            return "Invalid otp";

    }

}
