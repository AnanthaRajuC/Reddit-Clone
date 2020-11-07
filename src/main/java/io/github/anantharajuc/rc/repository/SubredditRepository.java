package io.github.anantharajuc.rc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.rc.model.Subreddit;

/**
 * Repository class for <code>Subreddit</code> domain object. 
 */
@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> 
{
	Optional<Subreddit> findByName(String subredditName);
}
