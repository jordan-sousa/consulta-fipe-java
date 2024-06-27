package br.com.jordan.consultaFipe;

import br.com.jordan.consultaFipe.controller.FipeController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaFipeApplication.class, args);
	}

	public void run(String... args) throws Exception {
		FipeController fipeController = new FipeController();
		fipeController.consultarFipe();
	}

}
