package main;

import model.Comment;
import proxies.EmailCommentNotificationProxy;
import repository.DBCommentRepository;
import services.CommentService;

public class main {
    public static void main(String[] args) {

        // creates the isntances for the dependencies
        var commentRepository = new DBCommentRepository();
        var commentNotificationProxy = new EmailCommentNotificationProxy();

        // creates the instances of the service class and providing the dependencies
        var commentService = new CommentService(commentRepository, commentNotificationProxy);

        var comment = new Comment("Davidson", "Demo Comment");

        commentService.publishComment(comment);
        
    }
}
