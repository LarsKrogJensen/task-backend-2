package se.lars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
public class TaskBackend2Application {

	public static void main(String[] args) {
		SpringApplication.run(TaskBackend2Application.class, args);
	}
}
