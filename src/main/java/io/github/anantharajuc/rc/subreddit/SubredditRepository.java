package io.github.anantharajuc.rc.subreddit;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for <code>Subreddit</code> domain object. 
 */
@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> 
{
	Optional<Subreddit> findByName(String subredditName);
}
