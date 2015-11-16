package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kni.forms.UserCreateForm;
import pl.kni.models.Registration;
import pl.kni.models.User;
import pl.kni.repositories.RegistrationRepository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by Maciej on 16.11.2015.
 */
@Transactional
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public Registration create(UserCreateForm form, String baseUrl) {
        Registration registration = new Registration(form);
        registration.setToken(tokenService.generateToken());
        registrationRepository.save(registration);
        emailService.sendConfirmationMail(registration,baseUrl);
        return registration;
    }

    @Override
    public User confirm(String token, String email) {

        System.out.println("\n\n\n\ntoken: "+token);
        System.out.println("email: "+email+"\n\n\n\n");

        Registration registration = registrationRepository.findByEmailAndToken(email,token);
        if (registration == null) {
            System.out.println("REGISTRATION IS NULL");
            return null;
        }
        if (!registration.isValid()){
            registrationRepository.delete(registration);
            System.out.println("REGISTRATION NOT VALID");
            return null;
        }
        User user = userService.create(registration);
        registrationRepository.delete(registration);
        return user;
    }

    @Override
    public boolean emailAvailable(String email) {
        Registration registration = registrationRepository.findOneByEmail(email);
        return registration==null;
    }
}
