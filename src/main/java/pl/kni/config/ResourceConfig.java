package pl.kni.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
        return container -> container.addErrorPages(new ErrorPage(MultipartException.class, "/uploadError"));
    }

}
