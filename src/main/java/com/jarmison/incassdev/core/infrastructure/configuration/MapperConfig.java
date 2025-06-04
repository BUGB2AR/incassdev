package com.jarmison.incassdev.core.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jarmison.incassdev.core.mapper.AudienciaMapper;
import com.jarmison.incassdev.core.mapper.ProcessoMapper;

@Configuration
public class MapperConfig {

    @Bean
    public AudienciaMapper audienciaMapper() {
        return AudienciaMapper.INSTANCE;
    }

    @Bean
    public ProcessoMapper processoMapper() {
        return ProcessoMapper.INSTANCE;
    }
}