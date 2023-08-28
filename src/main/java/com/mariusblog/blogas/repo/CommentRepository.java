package com.mariusblog.blogas.repo;

import com.mariusblog.blogas.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
