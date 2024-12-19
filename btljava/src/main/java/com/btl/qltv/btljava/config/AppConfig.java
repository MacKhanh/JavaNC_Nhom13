package com.btl.qltv.btljava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {
	
	@Bean
    public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new MappingJackson2HttpMessageConverter(customObjectMapper()));
	    return restTemplate;
	}
	private ObjectMapper customObjectMapper() {
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.registerModule(new ParameterNamesModule());
	    objectMapper.registerModule(new Jdk8Module());
	    objectMapper.registerModule(new JavaTimeModule());
	    return objectMapper;
	}
}
