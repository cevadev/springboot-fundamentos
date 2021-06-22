package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	// configurando el Log
	private final Log logger = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;

	// declaramos nuestra dependencia personalizada
	private MyBean myBean;

	private MyBeanWithDependency myBeanWithDependency;

	private MyBeanWithProperties myBeanWithProperties;

	// Iyectamos nuestra clase UserPojo
	private UserPojo userPojo;

	// inyeccion de dependencia en el constructor
	public FundamentosApplication(@Qualifier("componentSecondImplement") ComponentDependency componentDependency,
								  MyBean myBean,
								  MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo)
	{
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
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

		// Llamamos a los valores dentro del archivo application.properties
		System.out.println(myBeanWithProperties.printProperties());

		// accedemos a las propiedades de UserPojo
		System.out.println("Email: " + userPojo.getEmail());
		System.out.println("Password: "+ userPojo.getPassword());
		System.out.println("Age: " + userPojo.getAge());

		// Lanzamos un error via log
		logger.error("oops.. se produjo un error...");
	}
}