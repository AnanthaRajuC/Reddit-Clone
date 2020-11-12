package io.github.anantharajuc.rc.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.rc.model.ApplicationSetings;
import io.github.anantharajuc.rc.repository.ApplicationSettingsRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Service
public class AppServiceImpl implements AppService
{
	private String applicationName;
	
	@Autowired
	private ApplicationSettingsRepository applicationSettingsRepository;
	
	@Override
	public void loadApplicationSettings() 
	{
		log.info("-----> Loading Application Settings Value");
		
		List<ApplicationSetings> applicationSettingsList = applicationSettingsRepository.findAll();
		
		HashMap<String, String> applicationSettingsHashMap = new HashMap<>(); 
		
		for(int i = 0; i< applicationSettingsList.size(); i++)
		{
			applicationSettingsHashMap.put(applicationSettingsList.get(i).getAppKey(), applicationSettingsList.get(i).getAppValue());
		}
		
		setApplicationName(applicationSettingsHashMap.get("applicationName"));
	}
}
