package com.DavJaveiro.helloWorldJPA.main;

import com.DavJaveiro.helloWorldJPA.configuration.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.function.Supplier;


public class Main {
    public static void main(String[] args) {

        var context = new
                AnnotationConfigApplicationContext(ProjectConfig.class); // criando uma instância do Spring Context

        Parrot x = new Parrot();
        x.setName("Kiki");

        // definindo um Supplier para retornar a instância
        Supplier<Parrot> parrotSupplier = () -> x;

        // chamando o méthod registerBean() for add the instance to the Spring context
        context.registerBean("parrot1", Parrot.class, parrotSupplier);

        // Para verificar se o bean está inserido no contexto, nós referenciamos o Parrot.class e printamos seu nome no console
        Parrot p = context.getBean(Parrot.class);

        System.out.println(p.getName());




    }
}