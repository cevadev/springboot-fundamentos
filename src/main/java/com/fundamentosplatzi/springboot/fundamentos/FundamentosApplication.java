package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponentDependency componentDependency;
	// inyeccion de dependencia en el constructor
	public FundamentosApplication(ComponentDependency componentDependency){
		this.componentDependency = componentDependency;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// ejecutamos la implementacion de la dependencia ComponentDependency
		componentDependency.saludar();
	}
}