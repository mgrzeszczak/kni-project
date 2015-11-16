package pl.kni.services;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Maciej on 16.11.2015.
 */
@Service
public class TokenServiceImpl implements TokenService {

    private Random random = new Random();

    @Override
    public String generateToken() {
        return new BigInteger(130, random).toString(32);
    }

}
