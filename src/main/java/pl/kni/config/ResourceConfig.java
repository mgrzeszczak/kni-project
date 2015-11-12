package pl.kni.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Created by Maciej on 16.10.2015.
 */
@Configuration
public class ResourceConfig extends WebMvcConfigurerAdapter {

    @Value("${file.location}")
    private String location;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/res/**").addResourceLocations("file:///"+location);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
                                                    new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"),
                                                    new ErrorPage(HttpStatus.BAD_REQUEST, "/400"),
                                                    new ErrorPage(HttpStatus.FORBIDDEN,"/404"),
                                                    new ErrorPage(MultipartException.class,"/html/errors/500.html"));
    }


}
