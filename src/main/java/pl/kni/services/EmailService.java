package pl.kni.services;

import pl.kni.models.Registration;

/**
 * Created by Maciej on 16.11.2015.
 */
public interface EmailService {

    void sendConfirmationMail(Registration registration, String baseUrl);

}
