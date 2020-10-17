package com.example.solr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class SolrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolrApplication.class, args);
	}

}
