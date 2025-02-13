package com.DavJaveiro.helloWorldJPA.main;

import com.DavJaveiro.helloWorldJPA.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(); // criando uma instância do Spring Context

        Parrot parrot = new Parrot();
        System.out.println("Hello world!");
    }
}