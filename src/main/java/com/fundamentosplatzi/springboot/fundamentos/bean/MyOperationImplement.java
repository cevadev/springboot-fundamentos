package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyOperationImplement implements MyOperation{
    @Override
    public int sum(int numero1, int numero2) {
        return numero1 + numero2;
    }
}
