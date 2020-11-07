package io.github.anantharajuc.rc.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Simple JavaBean domain object representing a user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user") 
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level=AccessLevel.PRIVATE)
public class User extends AuditEntity
{
	private static final long serialVersionUID = 1L;
	
	@Size(min=3, max = 15, message="username must be between 5 and 50 characters.")
	@NotBlank(message = "Username is required")
	@Column(name="username")
    String username;
	
	@Size(min=3, max = 15, message="password must be between 5 and 50 characters.")
    @NotBlank(message = "Password is required")
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @Column(name="password")
    String password;
    
    @Email
    @NotEmpty(message = "Email is required")
    @Column(name="email")
    String email;

    @Column(name="enabled")
    boolean enabled;
}
