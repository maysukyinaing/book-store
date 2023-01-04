package com.ace.bookstore.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods("GET","POST","PUT","HEAD","DELETE");

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Optional<HttpMessageConverter<?>> foundConverter = converters
                .stream()
                .filter(c ->c instanceof AbstractJackson2HttpMessageConverter)
                .findAny();
        if (foundConverter.isPresent()) {
            AbstractJackson2HttpMessageConverter converter
                    = (AbstractJackson2HttpMessageConverter) foundConverter.get();
            converter.getObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,true);
        }
     }
}
