package org.example.sqch5ex1;

import org.example.sqch5ex1.configuration.ProjectConfig;
import org.example.sqch5ex1.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(ProjectConfig.class);

        CommentService cs1 = c.getBean("commentService", CommentService.class);
        CommentService cs2 = c.getBean("commentService", CommentService.class);

        boolean b1 = cs1 == cs2; // Because the two variables hold the same reference, the result of this operation is true;

        System.out.println(b1);
    }
}
