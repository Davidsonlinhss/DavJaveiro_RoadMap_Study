package com.DavJaveiro.helloWorldJPA.configuration;

import com.DavJaveiro.helloWorldJPA.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    Parrot parrot() {
        var p = new Parrot();
        p.setName("Koko");
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
