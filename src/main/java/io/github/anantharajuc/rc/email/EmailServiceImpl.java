package io.github.anantharajuc.rc.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import io.github.anantharajuc.rc.infra.exception.SpringRedditException;
import io.github.anantharajuc.rc.service.AppServiceImpl;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EmailServiceImpl implements EmailService
{
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;
	
	@Autowired
	private AppServiceImpl appServiceImpl;
	
	@Override
	public String mailContentBuilder(String mailContent) 
	{
		Context context = new Context();
		
		context.setVariable("message", mailContent);

		return templateEngine.process("mailTemplate", context);
	}

	@Override
	@Async
	public void sendMail(Email notificationEmail) 
	{
		appServiceImpl.loadApplicationSettings();

		javaMailSenderImpl.setUsername(appServiceImpl.getSpringMailUsername()); 
		javaMailSenderImpl.setPassword(appServiceImpl.getSpringMailPassword());
		javaMailSenderImpl.setPort(appServiceImpl.getSpringMailPort());
		javaMailSenderImpl.setProtocol(appServiceImpl.getSpringMailProtocol());
		javaMailSenderImpl.setHost(appServiceImpl.getSpringMailHost());
		
		MimeMessagePreparator messagePreparator = mimeMessage -> {
														          	MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

														            messageHelper.setFrom(appServiceImpl.getMailFrom());
														            messageHelper.setTo(notificationEmail.getRecipient());
														            messageHelper.setSubject(notificationEmail.getSubject());
														            messageHelper.setText(notificationEmail.getBody());
														            messageHelper.setReplyTo(appServiceImpl.getMailFrom());
														         };
        
        try 
        {
            javaMailSender.send(messagePreparator);
            
            log.info("Activation email sent!!");
        } 
        catch (MailException e) 
        {
            log.error("Exception occurred when sending mail", e);
            
            throw new SpringRedditException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
	}
}
