package net.lscrp.ucp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Value("${security.allowed-origin}")
    private String lscrpAllowedOrigin;


    @Value("${security.www-allowed-origin}")
    private String wwwLscrpAllowedOrigin;

    private final String devAllowedOrigin = "http://localhost:4200";

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods(
                                HttpMethod.GET.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.DELETE.name(),
                                HttpMethod.PATCH.name()
                        )
                        .allowedOrigins(devAllowedOrigin, lscrpAllowedOrigin, wwwLscrpAllowedOrigin)
                        .allowedHeaders("*");
            }
        };
    }
}