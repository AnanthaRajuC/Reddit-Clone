package io.github.anantharajuc.rc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import io.github.anantharajuc.rc.exceptions.SpringRedditException;
import io.github.anantharajuc.rc.model.NotificationEmail;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MailServiceImpl implements MailService
{
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.from}")
	private String mailFrom;
	
	@Override
	public String mailContentBuilder(String mailContent) 
	{
		Context context = new Context();
		
		context.setVariable("message", mailContent);
		
		return templateEngine.process("mailTemplate", context);
	}

	@Override
	@Async
	public void sendMail(NotificationEmail notificationEmail) 
	{
		MimeMessagePreparator messagePreparator = mimeMessage -> {
														          	MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
														          	
														            messageHelper.setFrom(mailFrom);
														            messageHelper.setTo(notificationEmail.getRecipient());
														            messageHelper.setSubject(notificationEmail.getSubject());
														            messageHelper.setText(notificationEmail.getBody());
														         };
        
        try 
        {
            mailSender.send(messagePreparator);
            
            log.info("Activation email sent!!");
        } 
        catch (MailException e) 
        {
            log.error("Exception occurred when sending mail", e);
            
            throw new SpringRedditException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
	}
}
