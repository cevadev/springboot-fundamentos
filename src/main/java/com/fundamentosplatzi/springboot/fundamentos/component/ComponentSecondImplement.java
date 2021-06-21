package com.fundamentosplatzi.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentSecondImplement implements ComponentDependency{
    @Override
    public void saludar() {
        System.out.println("Hello world desde mi componente 2");
    }
}
