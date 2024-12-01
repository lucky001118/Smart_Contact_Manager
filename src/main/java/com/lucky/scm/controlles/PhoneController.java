package com.lucky.scm.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lucky.scm.entities.User;
import com.lucky.scm.helpers.Message;
import com.lucky.scm.helpers.MessageType;
import com.lucky.scm.services.OtpService;
import com.lucky.scm.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PhoneController {

    @Autowired
    private UserService userService;

    @Autowired
    private OtpService otpService;

    @RequestMapping(value = "/update-number", method = RequestMethod.POST)
    public String updatePhone(@RequestParam("phone") String phoneno,
            @RequestParam("email") String email, HttpSession session) {
        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            Message message = Message.builder()
                    .content("The email id has been not registered try again with registered email id!")
                    .type(MessageType.red).build();
            session.setAttribute("message", message);
            return "user/dashboard";
        }

        user.setPhoneNumber(phoneno);
        User saveduser = this.userService.updateUsePassword(user);
        System.out.println(saveduser);

        System.out.println(phoneno);
        Message message = Message.builder().content("The Phone number has been changed successfully!")
                .type(MessageType.green).build();
        session.setAttribute("message", message);
        return "user/dashboard";
    }

    // number validation
    @RequestMapping("/vailidate-Phone")
    public String validatePhone() {
        return "user/vailidate_phone";
    }

    //
    @RequestMapping(value = "/vailidate-otp-check", method = RequestMethod.POST)
    public String vailidatePhonePre(@RequestParam("phone") String phoneno,
            @RequestParam("email") String email, HttpSession session) {

        System.out.println(phoneno);
        String realnumber = "+91" + phoneno;
        System.out.println(realnumber);

        session.setAttribute("emailId", email);

        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            Message message = Message.builder()
                    .content("The email id has been not registered try again with registered email id!")
                    .type(MessageType.red).build();
            session.setAttribute("message", message);
            return "user/vailidate_phone";
        }
        String otpstatus = this.otpService.sendOtpToPhone(realnumber);
        System.out.println(otpstatus);

        Message message = Message.builder().content("The OTP has been sent on your mobile number!")
                .type(MessageType.green).build();
        session.setAttribute("message", message);

        session.setAttribute("moblile-otp", otpstatus);

        return "user/phone_otp";
    }

    @RequestMapping(value = "/submit-phone-otp", method = RequestMethod.POST)
    public String submitOTP(@RequestParam("phone-otp") String phoneotp, HttpSession session) {

        System.out.println(phoneotp);
        String otpmsg = phoneotp;
        System.out.println("otpmsg: " + otpmsg);

        String result = this.otpService.vailidatedOtp(otpmsg);
        System.out.println(result);

        if (result != "Otp validated") {
            Message message = Message.builder().content("The OTP is invailid, try again with registered email id!")
                    .type(MessageType.red).build();
            session.setAttribute("message", message);

            if (otpmsg == null) {
                System.out.println("It is null");
            }
            return "user/phone_otp";
        }

        String email = (String) session.getAttribute("emailId");

        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            Message message = Message.builder()
                    .content("The email id has been not registered try again with registered email id!")
                    .type(MessageType.red).build();
            session.setAttribute("message", message);
            return "user/dashboard";
        }

        user.setPhoneVerified(true);
        User saveduser = this.userService.updatephoneVerified(user);
        System.out.println(saveduser);

        Message message = Message.builder().content("The OTP is validated successfully!").type(MessageType.green)
                .build();
        session.setAttribute("message", message);
        return "user/dashboard";
    }
}
