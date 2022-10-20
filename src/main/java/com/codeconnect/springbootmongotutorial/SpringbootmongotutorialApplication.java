package com.codeconnect.springbootmongotutorial;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class SpringbootmongotutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmongotutorialApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository){
		return args -> {

			Address address = new Address(
					"Wigan",
					"WN2",
					"England"
			);

			Student student = new Student(
					"Amber",
					"Ashley",
					"amberashley@email.com",
					Gender.FEMALE,
					address,
					List.of("Art", "Photography"),
					BigDecimal.TEN,
					LocalDateTime.now()

			);

			repository.insert(student);
		};
	}

}
