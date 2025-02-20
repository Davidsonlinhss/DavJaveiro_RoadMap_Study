package com.DavJaveiro.helloWorldJPA.main;

import com.DavJaveiro.helloWorldJPA.configuration.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {

        var context = new
                AnnotationConfigApplicationContext(ProjectConfig.class); // criando uma instância do Spring Context

        Person p = context.getBean(Person.class);

        System.out.println("Person's name: " + p.getName());
        System.out.println("Person's parrot: " + p.getParrot());

    }
}