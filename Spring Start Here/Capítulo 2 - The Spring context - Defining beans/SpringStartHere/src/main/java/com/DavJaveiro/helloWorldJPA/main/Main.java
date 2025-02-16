package com.DavJaveiro.helloWorldJPA.main;

import com.DavJaveiro.helloWorldJPA.configuration.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;


public class Main {
    public static void main(String[] args) {

        var context = new
                AnnotationConfigApplicationContext(ProjectConfig.class); // criando uma instância do Spring Context


        // 1. Usando o nome do bean explicitamente
        Parrot p1 = context.getBean("parrot1", Parrot.class);
        System.out.println(p1.getName());

        Parrot p2 = context.getBean("parrot2", Parrot.class);
        System.out.println(p2.getName());


        // 2. Usando primary, sem especificação direta
        Parrot primary = context.getBean(Parrot.class);
        System.out.println(primary.getName());



        //
        Map<String, Parrot> parrots = context.getBeansOfType(Parrot.class);

        parrots.forEach((name, parrot) -> {
            System.out.println("Bean name: " + name + ", Parrot Name : " + parrot.getName());
        });


    }
}