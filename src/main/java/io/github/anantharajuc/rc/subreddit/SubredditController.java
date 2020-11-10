package io.github.anantharajuc.rc.subreddit;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.rc.api.ResourcePaths;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value=ResourcePaths.Subreddit.V1.ROOT)
public class SubredditController 
{
	private final SubredditServiceImpl subredditServiceImpl;
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public SubredditDTO createSubreddit(@RequestBody SubredditDTO subredditDTO)
	{
		return subredditServiceImpl.save(subredditDTO);
	}
	
	@GetMapping()
	public List<Subreddit> getAllSubreddits()
	{
		return subredditServiceImpl.getAllSubreddits(); 
	}
}
