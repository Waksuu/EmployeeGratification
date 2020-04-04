package pl.kacper.starzynski.employeeGratification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "pl.kacper.starzynski.employeeGratification")
public class EmployeeGratification {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeGratification.class, args);
	}

}
