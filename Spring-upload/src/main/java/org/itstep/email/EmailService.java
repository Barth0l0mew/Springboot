package org.itstep.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;

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
    public void sendHtmlMessage(Email email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(email.getProperties());
        helper.setFrom(email.getFrom());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        String html = springTemplateEngine.process(email.getTemplate(), context);
        helper.setText(html, true);
        log.info ("Sending email: {} with html body: {}", email, html);
        javaMailSender.send(message);
    }
    public void sendEmailWithAttachment(Email email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true для вложений

        helper.setFrom(email.getFrom());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        helper.setText(email.getText());

        File attachment = new File(email.getAttachmentPath());
        System.out.println("email attachmant "+attachment.getName() );
        helper.addAttachment(attachment.getName(), attachment); // добавление вложения

        javaMailSender.send(message);
    }
}
