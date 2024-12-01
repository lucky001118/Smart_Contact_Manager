package com.lucky.scm.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lucky.scm.entities.User;
import com.lucky.scm.helpers.Message;
import com.lucky.scm.helpers.MessageType;
import com.lucky.scm.services.EmailService;
import com.lucky.scm.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ForgotController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    // email id form open controller
    @RequestMapping("/forgot")
    public String openEmailForm() {

        return "forgot_email_form";
    }

    // generate otp function
    public String genratedOtp() {
        int otp = (int) (Math.random() * 1000000);
        return String.format("%06d", otp);
    }

    // send the otp
    @RequestMapping(value = "/send-Otp", method = RequestMethod.POST)
    public String sendOTP(@RequestParam("email") String email, HttpSession session, Model model) {

        User userEmail = userService.getUserByEmail(email);
            if (userEmail==null) {
                //send the error message
                Message message = Message.builder().content("Email Do not exist").type(MessageType.red).build();
                session.setAttribute("message",message);
                return "forgot_email_form";
            }else{
                //send change password form
            }
        System.out.println(email);
        // generating 6 digit random number
        String otp = genratedOtp();
        System.out.println(otp);
        // String otpMassage = "Your otp for forget password is: "+otp;
        // sending the otp to the emailid
        this.emailService.sendEmailWithHtml(email, "Forget password otp from SCM", otp);
        // saving the otp in http session
        session.setAttribute("myotp", otp);
        session.setAttribute("email", email);
        model.addAttribute("otp", otp);

        Message message = Message.builder().content("Successfully send OTP! on your registed email id.").type(MessageType.green).build();
        session.setAttribute("message",message);

        return "verify_otp";
    }

    @RequestMapping(value = "/new-pass", method = RequestMethod.POST)
    public String newPass(@RequestParam("otp") int otp, HttpSession session) {

        String sessionOtp = (String)session.getAttribute("myotp");
        int myotp = Integer.parseInt(sessionOtp);
        System.out.println(otp);
        System.out.println(myotp);
        if (myotp == otp) {
            
            return "new-pass-form";
        }
        Message message = Message.builder().content("Please enter valid otp!").type(MessageType.red).build();
        session.setAttribute("message",message); 

        return "verify_otp";
    }

    //new password 
    @RequestMapping(value = "/change-password")
    public String newPassword(@RequestParam("new-password") String password,HttpSession session){
        String email = (String) session.getAttribute("email");
        User user = userService.getUserByEmail(email);
        user.setPassword(password);
        User saveduser = this.userService.updateUsePassword(user);
        System.out.println(saveduser);

        Message message = Message.builder().content("The password has been changed successfully! Now login").type(MessageType.green).build();
        session.setAttribute("message",message);
        return "login";
    }
}
