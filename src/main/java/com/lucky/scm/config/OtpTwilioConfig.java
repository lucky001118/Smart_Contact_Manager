package com.lucky.scm.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpTwilioConfig {
    private String accountSID;
    private String authToken;
    private String phoneNumber;

    @Override
    public String toString() {
        return "OtpTwilioConfig{" +
                "accountSID='" + accountSID + '\'' +
                ", authoToken='" + authToken + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

