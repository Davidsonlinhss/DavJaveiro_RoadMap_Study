package com.DavJaveiro.helloWorldJPA.configuration;

import com.DavJaveiro.helloWorldJPA.main.Parrot;
import com.DavJaveiro.helloWorldJPA.main.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.DavJaveiro.helloWorldJPA.main")
public class ProjectConfig {

}
