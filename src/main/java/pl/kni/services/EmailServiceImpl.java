package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.kni.models.Registration;

import javax.annotation.Resource;
import java.text.MessageFormat;

/**
 * Created by Maciej on 16.11.2015.
 */
@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;
    @Resource(name = "registerMessage")
    private SimpleMailMessage registerMessage;
    @Value(value="${mail.message.register.bodyTemplate}")
    private String registerMessageBodyTemplate;

    @Async
    public void sendConfirmationMail(Registration registration, String baseUrl) {
        SimpleMailMessage message = constructConfirmationEmailMessage(registration, baseUrl);
        mailSender.send(message);
    }

    private final SimpleMailMessage constructConfirmationEmailMessage(Registration registration, String baseUrl) {
        registerMessage.setTo(registration.getEmail());
        registerMessage.setText(MessageFormat.format(registerMessageBodyTemplate,baseUrl,registration.getEmail(),registration.getToken()));
        return registerMessage;
    }
}
