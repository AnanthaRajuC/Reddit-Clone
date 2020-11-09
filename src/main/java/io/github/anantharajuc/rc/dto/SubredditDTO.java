package io.github.anantharajuc.rc.dto;

import io.github.anantharajuc.rc.model.AuditEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=false)
public class SubredditDTO extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	String name;
    String description;
    Integer numberOfPosts;
}
