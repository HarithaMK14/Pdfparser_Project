package businesslogic;

import javax.mail.*;  
import javax.mail.internet.*;  
import java.util.Properties;  
  
public class Sendemail {  
public void send(final String from, final String password, String to, String sub, String msg){

	Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");
	Transport transport=null;
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
		transport.send(message);
		System.out.println("Approved Successfully");
		System.out.println("Check your e-mail");
	} catch(AddressException e){
		System.out.println("Address Exception occured..");
		e.printStackTrace();
	} catch (MessagingException e) {
		System.out.println("MessagingException Occured during Sending mail");
		e.printStackTrace();
	} finally{
		try{
			if(transport!=null)
				transport.close();
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}
}
}