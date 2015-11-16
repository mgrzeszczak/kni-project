package pl.kni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartException;

/**
 * Created by Maciej on 11.10.2015.
 */
@SpringBootApplication
public class Application {

    public static void main(String args[]){
        SpringApplication.run(Application.class,args);
    }

}
