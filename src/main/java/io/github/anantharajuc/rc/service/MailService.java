package io.github.anantharajuc.rc.service;

import io.github.anantharajuc.rc.model.NotificationEmail;

public interface MailService 
{
	String mailContentBuilder(String mailContent);
	
	void sendMail(NotificationEmail notificationEmail);
}
