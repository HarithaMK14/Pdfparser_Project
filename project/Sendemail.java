package com.altimetrik.training.project;

import javax.mail.*;  
import javax.mail.internet.*;  
import java.util.Properties;  
  
public class Sendemail {  
public void send(String from, String password, String to, String sub, String msg){

	Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");
	// get Session
	Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(from, password);
		}
	});
	// compose message
	try {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(sub);
		message.setText(msg);
		// send message
		Transport.send(message);
		System.out.println("Approved Successfully");
		System.out.println("Check your e-mail");
	} catch (MessagingException e) {
		System.out.println("Exception Occured during Sending mail");
		throw new RuntimeException(e);
}
}
}