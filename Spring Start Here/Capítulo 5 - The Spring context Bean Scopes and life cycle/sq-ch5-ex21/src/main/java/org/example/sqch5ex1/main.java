package org.example.sqch5ex1;

import org.example.configuration.ProjectConfig;
import org.example.sqch5ex1.comment.services.CommentService;
import org.example.sqch5ex1.user.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(ProjectConfig.class);


        // Gets the references of the two service beans in the Spring context
        CommentService cs1 = c.getBean(CommentService.class);
        UserService cs2 = c.getBean(UserService.class);


        // Compares the references for the repository dependency injected by Spring
        boolean b = cs1.getCommentRepository() == cs2.getCommentRepository();

        // Because the dependency (CommentRepository) is singleton, both service contain the same reference, so this line always prints "true"
        System.out.println(b);
    }
}
