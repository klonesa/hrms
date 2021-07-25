package com.bayrak.hrms.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Configuration
public class Config {

    @Value("${cloudinary.cloud-name}")
    @NotNull private String cloudName;

    @Value("${cloudinary.api-key}")
    @NotNull private String apiKey;

    @Value("${cloudinary.api-secret}")
    @NotNull private String apiSecret;

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true));
    }
}
