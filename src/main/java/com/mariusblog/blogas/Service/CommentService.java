package com.mariusblog.blogas.Service;

import com.mariusblog.blogas.entity.Comment;
import com.mariusblog.blogas.repo.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addCommentToTopic(Comment comment) {
        commentRepository.save(comment);
    }
}

