package io.github.anantharajuc.rc.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="vote") 
@Builder
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Vote extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@Column(name="vote_type")
	VoteType voteType;
    
    @NotNull
    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="id", referencedColumnName="id", insertable=false, updatable=false)
    Post post;
    
    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="id", referencedColumnName="id", insertable=false, updatable=false)
    User user;
}