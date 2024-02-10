package br.com.mtbassi.opovo.api.infra.model_mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfigurations {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}