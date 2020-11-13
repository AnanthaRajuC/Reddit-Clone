package io.github.anantharajuc.rc.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.github.anantharajuc.rc.audit.AuditEntity;
import io.github.anantharajuc.rc.security.user.model.User;

import static javax.persistence.FetchType.LAZY;

/**
 * Simple JavaBean domain object representing a comment.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comment") 
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Comment extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Size(min=3, max=500, message="comment must be between 3 and 500 characters.")
    String text;
    
    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="id", referencedColumnName="id", insertable=false, updatable=false)
    Post post;
    
    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="id", referencedColumnName="id", insertable=false, updatable=false)
    User user;
}