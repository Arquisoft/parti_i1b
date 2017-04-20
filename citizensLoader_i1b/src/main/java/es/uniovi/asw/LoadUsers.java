package es.uniovi.asw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.uniovi.asw.parser.Parser;
import es.uniovi.asw.persistence.CitizenRepository;

/**
 * Main application
 * 
 * @author Labra
 *
 */
@SpringBootApplication
public class LoadUsers {

	public static void main(String... args) {
		SpringApplication.run(LoadUsers.class, args);
	}

	// TODO
	@Bean
	public CommandLineRunner run(CitizenRepository repository) {
		return (args) -> {
			Parser.citizenRepository = repository;
			Parser.run(args);
		};
	}
}
