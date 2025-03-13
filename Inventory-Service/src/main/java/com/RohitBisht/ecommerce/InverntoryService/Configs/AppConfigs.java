package com.RohitBisht.ecommerce.InverntoryService.Configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfigs {

    @Bean
    public RestClient restClient() {
        return  RestClient
                .builder()
                .build();
    }
}
