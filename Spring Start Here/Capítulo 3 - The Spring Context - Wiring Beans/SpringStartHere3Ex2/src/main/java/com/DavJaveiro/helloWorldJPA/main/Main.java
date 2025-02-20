package com.DavJaveiro.helloWorldJPA.main;

import com.DavJaveiro.helloWorldJPA.configuration.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {

        var context = new
                AnnotationConfigApplicationContext(ProjectConfig.class); // criando uma instância do Spring Context



        Person person1 = context.getBean(Person.class);
        System.out.println("Person name: " + person1.getName());


        Parrot parrot = context.getBean(Parrot.class);
        System.out.println("Parrot name: " + parrot);



        System.out.println("Person's parrot: " + person1.getParrot());




    }
}