package io.github.anantharajuc.rc.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Simple JavaBean domain object representing a user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user") 
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level=AccessLevel.PRIVATE)
public class User extends AuditEntity
{
	private static final long serialVersionUID = 1L;
	
	@Size(min=3, max=15, message="username must be between 5 and 50 characters.")
	@Column(name="username", unique=true, nullable = false)
    String username;
	
	@Column(name="enabled")
    boolean enabled;

    @NotBlank(message = "Password is required")
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @Column(name="password", nullable = false)
    String password;
    
    @Email
    @NotEmpty(message = "Email is required")
    @Column(name="email", unique=true, nullable = false)
    String email;
}
