package io.github.anantharajuc.rc.subreddit;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubredditServiceImpl implements SubredditService
{
	private final ModelMapper modelMapper;
	private final SubredditRepository subredditRepository;
	
	@Override
	@Transactional
	public SubredditDTO save(SubredditDTO subredditDTO) 
	{
		Subreddit subreddit = subredditRepository.save(modelMapper.map(subredditDTO, Subreddit.class));

		return modelMapper.map(subreddit, SubredditDTO.class);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Subreddit> getAllSubreddits() 
	{
		return  subredditRepository.findAll();
	}
}
