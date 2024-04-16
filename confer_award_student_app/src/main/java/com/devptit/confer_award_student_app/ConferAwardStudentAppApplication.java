package com.devptit.confer_award_student_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ConferAwardStudentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferAwardStudentAppApplication.class, args);
	}

}
