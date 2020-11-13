package io.github.anantharajuc.rc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.rc.model.Post;
import io.github.anantharajuc.rc.security.user.model.User;
import io.github.anantharajuc.rc.subreddit.Subreddit;

import java.util.List;

/**
 * Repository class for <code>Post</code> domain object. 
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> 
{
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
