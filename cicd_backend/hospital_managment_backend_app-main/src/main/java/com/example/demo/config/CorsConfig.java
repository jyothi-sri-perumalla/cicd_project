package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins(
            "https://hospital-managment-frontned-app.vercel.app/",
            "https://hospital-managment-frontned-app.vercel.app/",
            "http://localhost:3000",
            "capacitor://localhost",
            "ionic://localhost")

        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH")
        .allowedHeaders("*")
        .allowCredentials(true)
        .maxAge(3600);
  }

  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration configuration = new CorsConfiguration();

    // Allow specific origins (remove trailing slashes)
    configuration.setAllowedOrigins(Arrays.asList(
        "https://hospital-managment-frontned-app.vercel.app/",
        "https://hospital-managment-frontned-app.vercel.app/",
        "http://localhost:3000",
        "capacitor://localhost",
        "ionic://localhost"));

    // Allow all methods
    configuration.setAllowedMethods(Arrays.asList(
        "GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH"));

    // Allow all headers
    configuration.setAllowedHeaders(Arrays.asList("*"));

    // Allow credentials
    configuration.setAllowCredentials(true);

    // Set max age for preflight cache
    configuration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return new CorsFilter(source);
  }
}
