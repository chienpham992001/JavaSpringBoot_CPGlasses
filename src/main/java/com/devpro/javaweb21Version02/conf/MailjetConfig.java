package com.devpro.javaweb21Version02.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;

@Configuration
public class MailjetConfig {
    
    @Value("${mailjet.apiKey}")
    private String apiKey;

    @Value("${mailjet.secretKey}")
    private String secretKey;

    @Bean
    public MailjetClient mailjetClient() {
        return new MailjetClient(apiKey, secretKey, new ClientOptions("v3.1"));
    }
}