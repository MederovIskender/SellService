package megacom.sellservicejava.services.impl;

import megacom.sellservicejava.services.SendSimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendSimpleMessageImpl implements SendSimpleMessage {
   @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendSimpleMessage(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("s");
        message.setTo(to);
        message.setSubject("Код потверждения");
        message.setText("Ваш код подтверждения: "+text);
        mailSender.send(message);
    }
}
