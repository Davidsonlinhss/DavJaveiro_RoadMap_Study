package org.example.sqch5ex1.configuration;

import org.example.sqch5ex1.services.CommentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean // adds the CommentService bean to the Spring context
    public CommentService commentService() {
        return new CommentService();
    }

}
