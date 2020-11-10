package io.github.anantharajuc.rc.email;

public interface EmailService 
{
	String mailContentBuilder(String mailContent);
	
	void sendMail(Email notificationEmail);
}
