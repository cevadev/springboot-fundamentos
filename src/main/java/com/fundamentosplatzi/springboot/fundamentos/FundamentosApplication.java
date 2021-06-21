package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponentDependency componentDependency;

	// declaramos nuestra dependencia personalizada
	private MyBean myBean;

	private MyBeanWithDependency myBeanWithDependency;

	// inyeccion de dependencia en el constructor
	public FundamentosApplication(@Qualifier("componentSecondImplement") ComponentDependency componentDependency,
								  MyBean myBean,
								  MyBeanWithDependency myBeanWithDependency)
	{
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// ejecutamos la implementacion de la dependencia ComponentDependency
		componentDependency.saludar();

		// llamamos a la implementacion de la dependencia
		myBean.print();

		// Llamamos a la implementacion de MyBeanWithDependency que esta en MyBeanWthDependencyImplement con el metodo
		// printWithDependency
		myBeanWithDependency.printWithDependency();
	}
}