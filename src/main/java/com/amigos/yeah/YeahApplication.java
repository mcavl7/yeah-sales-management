package com.amigos.yeah;

import com.amigos.yeah.services.S3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YeahApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(YeahApplication.class, args);
	}

	// Antiga command line para gerar dados no H2 - NÃO É MAIS USADA
	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("/home/dotc0m/Downloads/tijolo.jpg");
	}

	

}
