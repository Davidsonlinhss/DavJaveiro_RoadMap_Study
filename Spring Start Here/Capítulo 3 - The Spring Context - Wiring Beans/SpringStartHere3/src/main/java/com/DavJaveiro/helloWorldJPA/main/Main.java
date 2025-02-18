package com.DavJaveiro.helloWorldJPA.main;

import com.DavJaveiro.helloWorldJPA.configuration.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {

        var context = new
                AnnotationConfigApplicationContext(ProjectConfig.class); // criando uma instância do Spring Context

        // gets a reference to the Person bean from the Spring context
        Person person = context.getBean(Person.class);

        // get a reference to the Parrot bean from the Springng context
        Parrot parrot = context.getBean("parrot", Parrot.class);

        Parrot parrot1 = context.getBean("parrot2", Parrot.class);

        System.out.println("Person's name: " + person.getName());

        System.out.println("Parrot's name: " + parrot.getName());
        
        System.out.println("Parrot's name:" + parrot1.getName());

        // prints the person's parrot to prove that there's not yet a relationship between the instances
        System.out.println("Person's parrot: " + person.getParrot());




    }
}