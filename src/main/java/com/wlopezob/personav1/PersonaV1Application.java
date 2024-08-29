package com.wlopezob.personav1;

import com.wlopezob.personav1.config.CustomAppApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonaV1Application extends CustomAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonaV1Application.class, args);
	}

}
