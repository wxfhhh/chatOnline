
package com.chatOnline.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String toEmail,String subject,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toEmail);
        message.setSubject(subject);
        //String code=CodeGenerator.codeGenerator(6);
        message.setText(text);

        try{
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
