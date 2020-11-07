package io.github.anantharajuc.rc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.rc.model.RefreshToken;

import java.util.Optional;

/**
 * Repository class for <code>RefreshToken</code> domain object. 
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> 
{
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}