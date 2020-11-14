package io.github.anantharajuc.rc.security.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.anantharajuc.rc.audit.AuditEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@ApiModel(description="Models User table.")
public class User extends AuditEntity
{
	//Default Serial Version ID
	private static final long serialVersionUID = 1L;
	
	@Email
    @NotEmpty(message = "Email is required")
    @Column(name="email", unique=true, nullable = false)
	@ApiModelProperty(position=5, notes="Email address used to create the app account.", value="${User.email}", required=true, example="example@domain.com")
    String email;
	
	@Size(min=3, max=15, message="username must be between 5 and 50 characters.")
	@Column(name="username", unique=true, nullable = false)
	@ApiModelProperty(position=6, notes="A unique identifier used by a person.", value="${User.username}", required=true, example="user-1234")
    String username;

    @NotBlank(message = "Password is required")
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @Column(name="password", nullable = false)
    @ApiModelProperty(position=7, notes="A secret word/phrase used to gain access to the application.", value="${User.password}", example="$+r0nG10$$w0rD")
    String password;

    @Column(name="isEnabled")
    @ApiModelProperty(position=8, notes="Flag to mark id the account is enabled after token verification via email", value="${User.enabled}")
    boolean isEnabled;
    
    @Column(name="isAccountNonExpired")
	boolean isAccountNonExpired;
	
	@Column(name="isAccountNonLocked")
	boolean isAccountNonLocked;
	
	@Column(name="isCredentialsNonExpired")
	boolean isCredentialsNonExpired;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
}
