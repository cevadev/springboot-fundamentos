package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// configuracion de nuestros bean o dependencias propias
// @Configuration : springboot detectara que esta clase tendras opciones de configuracion
@Configuration
public class MyBeanConfiguration {
    /**
     * bean que retorna la interface
     */
    @Bean
    public MyBean beanOperation(){
        // retornamos la implementacion del Bean
        return new MyBeanSecondImplement();
    }

    @Bean
    public MyOperation beanSumOperation(){
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency beanOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }
}
