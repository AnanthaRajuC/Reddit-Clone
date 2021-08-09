package io.github.anantharajuc.rc.infra.security.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.rc.infra.security.authentication.model.VerificationToken;

import java.util.Optional;

/**
 * Repository class for <code>VerificationToken</code> domain object. 
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> 
{
    Optional<VerificationToken> findByToken(String token);
}
