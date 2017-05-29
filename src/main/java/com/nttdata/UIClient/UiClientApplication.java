package com.nttdata.UIClient;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com.nttdata.UIClient.*")
public class UiClientApplication  {

	//@Autowired
	//private CatalogClient catalogClient;
	
	public static final Logger logger = LoggerFactory.getLogger(UiClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UiClientApplication.class, args);
	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	

}