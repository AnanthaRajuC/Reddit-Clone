package io.github.anantharajuc.rc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.rc.dto.SubredditDTO;
import io.github.anantharajuc.rc.service.SubredditServiceImpl;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subreddit")
public class SubredditController 
{
	private final SubredditServiceImpl subredditServiceImpl;
	
	@PostMapping
	public SubredditDTO createSubreddit(@RequestBody SubredditDTO subredditDTO)
	{
		return subredditServiceImpl.save(subredditDTO);
	}
}
