package com.kb.java.university.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class Beans {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

