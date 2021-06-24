package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

	// Inyectamos el UserRepository como dependencia
	private UserRepository userRepository;

	// inyeccion de dependencia en el constructor
	public FundamentosApplication(@Qualifier("componentSecondImplement") ComponentDependency componentDependency,
								  MyBean myBean,
								  MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo,
								  UserRepository userRepository)
	{
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUserInDB();
		getUserByEmail();
		getOrderedUsersByName();
	}

	private void saveUserInDB(){
		User user1 = new User("Aron", "john@gmail.com", LocalDate.of(2021, 06, 24));
		User user2 = new User("Alberto", "alberto@gmail.com", LocalDate.of(2021, 06, 24));
		List<User> users = Arrays.asList(user1, user2);

		// para cada uno de los elementos (user) de la lista lo guardamos en la BD
		users.stream().forEach(userRepository::save);
	}

	private void getUserByEmail(){
		logger.info("User info: " + userRepository.findByUserEmail("alberto@gmail.com")
				// en caso que no se encuentre un User lanzamos una Exception
				.orElseThrow(()-> new RuntimeException("No existe User con ese email")));
	}

	private void getOrderedUsersByName(){
		userRepository.findAndSort("A", Sort.by("name").ascending())
			// stream -> por cada elemento de la List mostrams el User)
			.stream()
				.forEach(user -> logger.info("User ordered: " + user));
	}

	private void ejemplosAnteriores(){
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