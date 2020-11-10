package io.github.anantharajuc.rc;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.extern.log4j.Log4j2;

/**
 * Reddit Clone
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@SpringBootApplication
@Log4j2
@EnableAsync(proxyTargetClass=true)
public class RedditCloneApplication implements CommandLineRunner
{
	public static void main(String[] args) 
	{
		SpringApplication.run(RedditCloneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		log.info("Reddit Clone Application Started on {}", LocalDateTime.now());	
	}
}
