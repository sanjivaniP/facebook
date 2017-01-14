package edu.iiitb.facebook.action;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.iiitb.facebook.model.ForgotPasswordDao;

public class EmailAction {
String firstName;
private String from;
private String password;

private String to;
private String subject;
private String body;
int loginId;
String emailId;

static Properties properties = new Properties();
static
{
   properties.put("mail.smtp.host", "smtp.gmail.com");
   properties.put("mail.smtp.socketFactory.port", "465");
   properties.put("mail.smtp.socketFactory.class",
                  "javax.net.ssl.SSLSocketFactory");
   properties.put("mail.smtp.auth", "true");
   properties.put("mail.smtp.port", "465");
}

public String sendEmail(){
	Random generator = new Random(); 
	int code = generator.nextInt(1000000) + 100000;
	System.out.println("no. "+code);
	ForgotPasswordDao storeCode  = new ForgotPasswordDao();
	System.out.println("in email action email id"+emailId);
	if(storeCode.storeSecureCode(1, code).equals("success"))
	{
		try
	    {
	    	setFrom("nitika.panwar89@gmail.com");
	    	setBody("somebody has requested for password reset. Is it you?"+
	    	"Please enter the following password reset code:"+code);
	    	setPassword("JAImataDI99");
	    	setTo(emailId);
	    	setSubject("facebook forgot password");
	       Session session = Session.getDefaultInstance(properties,  
	          new javax.mail.Authenticator() {
	          protected PasswordAuthentication 
	          getPasswordAuthentication() {
	          return new 
	          PasswordAuthentication(from, password);
	          }});

	       Message message = new MimeMessage(session);
	       message.setFrom(new InternetAddress(from));
	       message.setRecipients(Message.RecipientType.TO, 
	          InternetAddress.parse(to));
	       message.setSubject(subject);
	       message.setText(body);
	       Transport.send(message);
	    }
	    catch(Exception e)
	    {
	      
	       e.printStackTrace();
	    }
	}

	System.out.println("in show"+ firstName);
	System.out.println("in show loginId"+ loginId);
	return "success";
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public static Properties getProperties() {
	return properties;
}
public static void setProperties(Properties properties) {
	EmailAction.properties = properties;
}
public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public int getLoginId() {
	return loginId;
}
public void setLoginId(int loginId) {
	this.loginId = loginId;
}
}
