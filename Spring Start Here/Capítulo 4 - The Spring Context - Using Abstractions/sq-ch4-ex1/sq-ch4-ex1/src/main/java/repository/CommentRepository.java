package repository;

import model.Comment;

public interface CommentRepository {
    void storeComment(Comment comment);
}
