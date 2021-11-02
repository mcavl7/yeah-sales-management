package com.amigos.yeah;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YeahApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(YeahApplication.class, args);
	}

	// Antiga command line para gerar dados no H2 - NÃO É MAIS USADA
	@Override
	public void run(String... args) throws Exception {
		
	}

	

}
