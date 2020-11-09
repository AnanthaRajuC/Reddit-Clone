package io.github.anantharajuc.rc.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.rc.dto.SubredditDTO;
import io.github.anantharajuc.rc.model.Subreddit;
import io.github.anantharajuc.rc.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class SubredditServiceImpl implements SubredditService
{
	private final ModelMapper modelMapper;
	private final SubredditRepository subredditRepository;
	
	@Override
	public SubredditDTO save(SubredditDTO subredditDTO) 
	{		
		Subreddit subreddit = subredditRepository.save(modelMapper.map(subredditDTO, Subreddit.class));
		
		log.info("ssimpl "+ SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getName());	

		return modelMapper.map(subreddit, SubredditDTO.class);
	}
}
