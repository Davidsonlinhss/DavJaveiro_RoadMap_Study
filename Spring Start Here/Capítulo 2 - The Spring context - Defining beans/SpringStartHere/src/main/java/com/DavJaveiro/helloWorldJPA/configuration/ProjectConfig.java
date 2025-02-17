package com.DavJaveiro.helloWorldJPA.configuration;

import com.DavJaveiro.helloWorldJPA.main.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.DavJaveiro.helloWorldJPA.main")
public class ProjectConfig {

    // Adicionando multiplos beans para tipos iguais ao Sprin context
    @Bean
    Parrot parrot1() {
        var p = new Parrot();
        p.setName("Koko");
        return p;
    }

//    @Primary // quando definimos primary, um Spring escolherá automaticamente
    @Bean
    Parrot parrot2() {
        var p = new Parrot();
        p.setName("Miki");
        return p;
    }

    @Bean
    Parrot parrot3() {
        var p = new Parrot();
        p.setName("Kiki");
        return p;
    }

    @Bean
    @Primary // o Spring escolhe automaticamente esse bean no contexto do Spring
    Parrot primaryParrot() {
        var p = new Parrot();
        p.setName("Primary Parrot");
        return p;
    }
    
    // adds the string "hello" to the Spring context
    @Bean
    String hello() {
        return "Hello World";
    }

    @Bean
    Integer ten(){
        return 10;
    }
}
