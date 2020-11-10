package io.github.anantharajuc.rc.subreddit;

import java.util.List;

public interface SubredditService 
{
	SubredditDTO save(SubredditDTO subredditDTO);
	
	List<Subreddit> getAllSubreddits();
}
