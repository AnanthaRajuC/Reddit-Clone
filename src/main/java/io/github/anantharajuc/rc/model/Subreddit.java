package io.github.anantharajuc.rc.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sub_reddit") 
@Builder
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Subreddit extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@Size(min=5, max=50, message="Community name must be between 5 and 50 characters.")
	@NotBlank(message = "Community name is required")
	@Column(name="name")
    String name;
    
	@Size(min=5, max=50, message="description must be between 5 and 50 characters.")
    @NotBlank(message = "Description is required")
    @Column(name="description")
    String description;
    
    @OneToMany(fetch = LAZY)
    List<Post> posts;
    
    @ManyToOne(fetch = LAZY)
    User user;
}