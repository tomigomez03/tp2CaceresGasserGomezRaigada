package com.tuti.desi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Tp2CaceresGasserGomezRaigadaApplication implements WebMvcConfigurer  {

	public static void main(String[] args) {
		SpringApplication.run(Tp2CaceresGasserGomezRaigadaApplication.class, args);
	}
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/static/**")
	                .addResourceLocations("classpath:/static/")
	                .setCachePeriod(0);
	    }

}
