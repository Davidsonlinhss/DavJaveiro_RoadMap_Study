package services;

import model.Comment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import proxies.CommentNotificationProxy;
import repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentNotificationProxy commentNotificationProxy;

    // We would have to uso @Autowired if the class had more than one constructor;
    public CommentService(
            CommentRepository commentRepository,
            @Qualifier("EMAIL") CommentNotificationProxy commentNotificationProxy) {
            this.commentRepository = commentRepository;
            this.commentNotificationProxy = commentNotificationProxy;
    }
    // Spring uses this constructor to create the bean and injects references from its context in the parameters when creating the instance

    public void publishComment(Comment comment) {
        commentRepository.storeComment(comment);
        commentNotificationProxy.sendComment(comment);
    }
}
