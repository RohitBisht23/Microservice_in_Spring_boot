package com.RohitBisht.ecommerce.Order.Service.Configs;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigs {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
