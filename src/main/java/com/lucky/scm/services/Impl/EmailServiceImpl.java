package com.lucky.scm.services.Impl;

// import java.io.FileNotFoundException;
// import java.io.FileReader;
import java.nio.file.Files;
// import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lucky.scm.services.EmailService;

// import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender eMailSender;

    @Value("${spring.mail.properties.domain_name}")
    private String domainName;

    @Override
    public void sendEmail(String to, String subject, String body) {

        // SimpleMailMessage message = new SimpleMailMessage();
        // message.setTo(to);
        // message.setSubject(subject);
        // message.setText(body);
        // message.setFrom(domainName);
        // eMailSender.send(message);

        try{
            MimeMessage message = eMailSender.createMimeMessage();
    
            message.setFrom(domainName);
            message.setRecipients(MimeMessage.RecipientType.TO, to);
            message.setSubject(subject);
    
            Path fileName
                = Path.of("src\\main\\resources\\templates\\emailVerify.html");
     
            // Now calling Files.readString() method to
            // read the file
            String msgContent = Files.readString(fileName);
           msgContent =msgContent.replace("https://yahaparactualverifylinkhai", body);
                
            message.setContent(msgContent, "text/html; charset=utf-8");
    
            //sending the mail
            eMailSender.send(message);
    
            }catch(Exception e){
    
                e.printStackTrace();
            }

    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String body) {

        try{
        MimeMessage message = eMailSender.createMimeMessage();

        message.setFrom(domainName);
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject(subject);

        Path fileName
            = Path.of("src\\main\\resources\\templates\\emailhtmltemplate.html");
 
        // Now calling Files.readString() method to
        // read the file
        String str = Files.readString(fileName);
        str = str.replace("123456", body);
            
        message.setContent(str, "text/html; charset=utf-8");

        //sending the mail
        eMailSender.send(message);

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailWithAttachment() {

        throw new UnsupportedOperationException("Unimplemented method 'sendEmailWithAttachment'");
    }

}
