package io.github.anantharajuc.rc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.rc.model.Post;
import io.github.anantharajuc.rc.model.User;
import io.github.anantharajuc.rc.model.Vote;

import java.util.Optional;

/**
 * Repository class for <code>Vote</code> domain object. 
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> 
{
    Optional<Vote> findTopByPostAndUserOrderByIdDesc(Post post, User currentUser);
}