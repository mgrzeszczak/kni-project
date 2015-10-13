package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kni.models.RememberMeToken;
import pl.kni.repositories.RememberMeTokenRepository;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by Maciej on 13.10.2015.
 */
@Service
@Transactional
public class RememberMePersistentTokenRepository implements PersistentTokenRepository {

    @Autowired
    private RememberMeTokenRepository rememberMeTokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        RememberMeToken token = new RememberMeToken();
        token.setDate(persistentRememberMeToken.getDate());
        token.setSeries(persistentRememberMeToken.getSeries());
        token.setUsername(persistentRememberMeToken.getUsername());
        token.setTokenValue(persistentRememberMeToken.getTokenValue());
        rememberMeTokenRepository.save(token);
    }

    @Override
    public void updateToken(String s, String s1, Date date) {
        RememberMeToken token = rememberMeTokenRepository.findBySeries(s);
        if (token==null) return;
        token.setTokenValue(s1);
        token.setDate(date);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String s) {
        RememberMeToken token = rememberMeTokenRepository.findBySeries(s);
        return token==null? null : new PersistentRememberMeToken(token.getUsername(),token.getSeries(),token.getTokenValue(),token.getDate());
    }

    @Override
    public void removeUserTokens(String s) {
        rememberMeTokenRepository.deleteByUsername(s);
    }
}
