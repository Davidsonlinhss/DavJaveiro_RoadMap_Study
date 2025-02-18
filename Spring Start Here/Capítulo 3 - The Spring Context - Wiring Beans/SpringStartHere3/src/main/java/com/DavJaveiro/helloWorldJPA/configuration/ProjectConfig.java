package com.DavJaveiro.helloWorldJPA.configuration;

import com.DavJaveiro.helloWorldJPA.main.Parrot;
import com.DavJaveiro.helloWorldJPA.main.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public Parrot parrot() {
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean
    public Parrot parrot2() {
        Parrot p1 = new Parrot();
        p1.setName("Lola");
        return p1;
    }

    @Bean
    public Person person() {
        Person p = new Person();
        p.setName("Davidson");
        p.setParrot(parrot2()); // set the reference of the parrot bean to the person's parrot attribute
        return p;
    }

}
