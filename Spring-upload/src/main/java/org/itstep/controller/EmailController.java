package org.itstep.controller;

import org.itstep.email.Email;
import org.itstep.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EmailController {
    @Autowired
    EmailService emailService;
    @GetMapping("/sendsimple")
    public String sendSimple (Model model){
        Email email = new Email();
        email.setTo("dim140192@gmail.com");
        email.setFrom("barth0l0mew@mail.ru");
        email.setSubject("Simple text");
        email.setText("Simple simple");
        emailService.sendSimpleMessage(email);
        return "success";
    }
    @GetMapping("/send")
    public String send (Model model) throws MessagingException {
        Email email = new Email();
        email.setTo("dim140192@gmail.com");
        email.setFrom("barth0l0mew@mail.ru");
        email.setSubject("Simple text");
        email.setTemplate("email.html");
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", "Ashish");
        properties.put("subscriptionDate",LocalDate.now().toString());
        properties.put("technologies", Arrays.asList("Python", "Go", "C#"));
        email.setProperties(properties);
        emailService.sendHtmlMessage(email);
        return "success";
    }
    @PostMapping("/sendfile")
    public String sendEmail() throws MessagingException {
        try {
            Email email = new Email();
            email.setTo("dim140192@gmail.com");
            email.setFrom("barth0l0mew@mail.ru");
            email.setSubject("Simple text");
            email.setText("email.html");
            email.setAttachmentPath("C:/Users/gk/Springboot/Spring-upload/src/main/resources/uploaded/labeo.jpg");
            emailService.sendEmailWithAttachment(email);
            return "Email sent successfully!";
        } catch (MailException e) {
            return "Error while sending email: " + e.getMessage();
        }
    }

}
