package br.edu.insper.aps2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.edu.insper.aps2")
public class Aps2Application {

	public static void main(String[] args) {
		SpringApplication.run(Aps2Application.class, args);
	}

}
