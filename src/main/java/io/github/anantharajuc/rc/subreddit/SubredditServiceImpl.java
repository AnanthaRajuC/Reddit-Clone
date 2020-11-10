package io.github.anantharajuc.rc.subreddit;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubredditServiceImpl implements SubredditService
{
	private final ModelMapper modelMapper;
	private final SubredditRepository subredditRepository;
	
	@Override
	public SubredditDTO save(SubredditDTO subredditDTO) 
	{
		Subreddit subreddit = subredditRepository.save(modelMapper.map(subredditDTO, Subreddit.class));

		return modelMapper.map(subreddit, SubredditDTO.class);
	}
}
