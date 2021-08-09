package io.github.anantharajuc.rc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.rc.domain.model.Comment;
import io.github.anantharajuc.rc.domain.model.Post;
import io.github.anantharajuc.rc.infra.security.user.model.User;

import java.util.List;

/**
 * Repository class for <code>Comment</code> domain object. 
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> 
{
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}