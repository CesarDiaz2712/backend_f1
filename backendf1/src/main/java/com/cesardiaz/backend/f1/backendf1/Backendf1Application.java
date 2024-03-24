package com.cesardiaz.backend.f1.backendf1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@Configuration
@PropertySource(value = { "classpath:app.properties" })
// @EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class Backendf1Application {

	public static void main(String[] args) {
		SpringApplication.run(Backendf1Application.class, args);
	}


	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-6"));
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(df);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean
    // public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        
    //     return builder -> {
            
    //         // formatter
    //         DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //         DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
    //         // deserializers
    //         builder.deserializers(new LocalDateDeserializer(dateFormatter));
    //         builder.deserializers(new LocalDateTimeDeserializer(dateTimeFormatter));
            
    //         // serializers
    //         builder.serializers(new LocalDateSerializer(dateFormatter));
    //         builder.serializers(new LocalDateTimeSerializer(dateTimeFormatter));
    //     };
    // }


}
