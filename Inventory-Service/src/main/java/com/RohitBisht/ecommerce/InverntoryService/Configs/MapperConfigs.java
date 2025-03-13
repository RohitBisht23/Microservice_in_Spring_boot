package com.RohitBisht.ecommerce.InverntoryService.Configs;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class MapperConfigs {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

}
