package com.eddyemb.EventoManager.mapper;

import com.eddyemb.EventoManager.dto.request.response.CommunityResponse;
import com.eddyemb.EventoManager.model.Community;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ModelMapperConfig {
    @Bean //Permite manusear Notacao externa
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
