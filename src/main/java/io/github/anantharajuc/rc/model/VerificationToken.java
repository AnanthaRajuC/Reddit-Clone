package io.github.anantharajuc.rc.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.rc.audit.AuditEntity;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="verification_token")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level=AccessLevel.PRIVATE)
public class VerificationToken extends AuditEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="token")
	String token;
	
    @Column(name="expiry_date")
    Instant expiryDate;
	
    @OneToOne(fetch=LAZY)
    User user;
}
