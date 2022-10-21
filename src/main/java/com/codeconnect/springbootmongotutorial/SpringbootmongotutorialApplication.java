package com.codeconnect.springbootmongotutorial;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
	CommandLineRunner runner(StudentRepository repository, MongoTemplate template){
		return args -> {

			Address address = new Address(
					"Wigan",
					"WN2",
					"England"
			);

			String email = "amberashley@email.com";
			Student student = new Student(
					"Amber",
					"Ashley",
					email,
					Gender.FEMALE,
					address,
					List.of("Art", "Photography"),
					BigDecimal.TEN,
					LocalDateTime.now()

			);

			//less clean way
			//usingMongoTemplateAndQuery(repository, template, email, student);
			repository.findStudentByEmail(email)
					.ifPresentOrElse(s -> {
						System.out.printf(s.getEmail() + " already exists");
					}, () -> {repository.insert(student);});

		};
	}

	private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate template, String email, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		List<Student> students = template.find(query, Student.class);
		if(students.size() > 1) throw new IllegalStateException("More than one student returned with the email " + email);
		if (students.isEmpty()) repository.insert(student);
	}

}
