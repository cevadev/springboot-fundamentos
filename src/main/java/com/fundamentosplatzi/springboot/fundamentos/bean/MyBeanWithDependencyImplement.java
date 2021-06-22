package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    // log
    private final Log logger = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    private MyOperation myOperation;

    // inyectamos la dependencia MyOperation
    public MyBeanWithDependencyImplement(MyOperation myOperation){
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        // imprimimos info con el log
        logger.info("dentro de printWithDependency method");

        // Llamamos a la operacion
        System.out.println("La suma es: " + myOperation.sum(5, 5));
        logger.debug("se hizo la suma de 5+5");
        System.out.println("Hello desde la implementacion de un bean con dependencia");
    }
}
