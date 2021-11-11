package br.com.zup.transacao.config;

import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class TestConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
    }

}
