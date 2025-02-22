package main;

import configuration.ProjectConfiguration;
import model.Comment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.CommentService;

public class main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(
                ProjectConfiguration.class
        );

        Comment comment = new Comment();
        comment.setAuthor("David");
        comment.setText("Demo comment");

        CommentService commentService = context.getBean(CommentService.class);

        commentService.publishComment(comment);

    }
}
