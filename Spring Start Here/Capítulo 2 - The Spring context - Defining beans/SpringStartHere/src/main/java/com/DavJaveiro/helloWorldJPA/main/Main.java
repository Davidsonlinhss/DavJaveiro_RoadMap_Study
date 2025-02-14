package com.DavJaveiro.helloWorldJPA.main;

import com.DavJaveiro.helloWorldJPA.Parrot;
import com.DavJaveiro.helloWorldJPA.configuration.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class); // criando uma instância do Spring Context

        Parrot p = context.getBean(Parrot.class);
        System.out.println(p.getName());
        
        String s = context.getBean(String.class);
        System.out.println(s);

        Integer n = context.getBean(Integer.class);
        System.out.println(n);


    }
}