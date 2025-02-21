package proxies;

import model.Comment;

public class EmailCommentNotificationProxy implements CommentNotificationProxy {

    @Override
    public void sendComment(Comment comment) {
        System.out.println("Send notification for comment " + comment.getText());
    }
}
