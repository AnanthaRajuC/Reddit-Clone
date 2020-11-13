package io.github.anantharajuc.rc.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.lang.Nullable;

import io.github.anantharajuc.rc.audit.AuditEntity;
import io.github.anantharajuc.rc.security.user.model.User;
import io.github.anantharajuc.rc.subreddit.Subreddit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.LAZY;

/**
 * Simple JavaBean domain object representing a post.
 */
@Data
@Entity
@Table(name="post") 
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Post extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Post Name cannot be empty or Null")
	@Size(min=3, max=50, message="post name must be between 3 and 50 characters.")
	@Column(name="post_name")
    String postName;
    
    @Nullable
    @Size(min=5, max=800, message="url must be between 5 and 800 characters.")
    @Column(name="url")
    String url;
    
    @Nullable
    @Size(min=5, max=50, message="description must be between 5 and 50 characters.")
    @Lob
    @Column(name="description")
    String description;
    
    @Builder.Default
    @Column(name="vote_count")
    Integer voteCount=0;
    
    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="id", referencedColumnName="id", insertable=false, updatable=false)
    User user;
    
    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="id", referencedColumnName="id", insertable=false, updatable=false)
    Subreddit subreddit;
}