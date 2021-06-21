package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    private MyOperation myOperation;

    // inyectamos la dependencia MyOperation
    public MyBeanWithDependencyImplement(MyOperation myOperation){
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        // Llamamos a la operacion
        System.out.println("La suma es: " + myOperation.sum(5, 5));

        System.out.println("Hello desde la implementacion de un bean con dependencia");
    }
}
