package org.itstep.controller;

import org.itstep.email.Email;
import org.itstep.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {
    @Autowired
    EmailService emailService;
    @GetMapping("/sendsimple")
    public String sendSimple (Model model){
        Email email = new Email();
        email.setTo("barth0l0mew@mail.ru");
        email.setFrom("dim140192@gmail.com");
        email.setSubject("Simple text");
        email.setText("Simple simple");
        emailService.sendSimpleMessage(email);
        return "success";
    }
}
