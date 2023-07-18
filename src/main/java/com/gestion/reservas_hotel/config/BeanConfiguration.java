package com.gestion.reservas_hotel.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;


@Configuration
public class BeanConfiguration {
    private static final String dateFormat = "yyyy-MM-dd";

    @Bean
    ModelMapper modelMapper() { return new ModelMapper(); }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {builder.serializers(
                new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));};
        }

}
