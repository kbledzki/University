package com.kb.java.university.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Bean {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

