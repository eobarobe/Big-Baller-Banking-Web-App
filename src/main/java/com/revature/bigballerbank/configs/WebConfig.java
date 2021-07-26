package com.revature.bigballerbank.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET, POST, PUT")
                        .allowedHeaders("Content-Type")
                        .exposedHeaders("*");
            }
            /*
                registry.addMapping() has
                    .allowedOrigins
                    .allowedMethods
                    .allowedHeaders
                    .exposedHeaders()
                    .maxAge
                    .allowCredentials
            */
        };
    }
}
