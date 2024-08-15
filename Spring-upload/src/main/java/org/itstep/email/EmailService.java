package org.itstep.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    //почтовый отправитель
    private final JavaMailSender javaMailSender;
    //обработчик шаблонов
    private final SpringTemplateEngine springTemplateEngine;
    public void sendSimpleMessage (Email email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        log.info     ("Sending email {}",email.getText());


        javaMailSender.send(message);
    }
}
