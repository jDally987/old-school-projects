import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendSMS {
	public static void main(String[] args) {
		String message_subject = 	"A Special Message";
		String message_body = 		"Hello, world!";
		
		String message_to = 		"3015146165@vtext.com";
		String message_from = 		"jdchaney@mix.wvu.edu";

		String config_host = 		"exsmtp.wvu.edu";
		String config_port =		"465";
		String config_user = 		"jdchaney";
		String config_pswd = 		"BLackb1rd";

		Properties props = new Properties();

		props.put("mail.smtp.host", config_host);
		props.put("mail.smtp.port", config_port);
		props.put("mail.smtp.user", config_user);

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");

		props.put("mail.smtp.socketFactory.port", config_port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(true);
	
			MimeMessage message = new MimeMessage(session);
			message.setText(message_body);
			message.setSubject(message_subject);
			message.setFrom(new InternetAddress(message_from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(message_to));
			message.saveChanges();
	
			Transport transport = session.getTransport("smtp");
			transport.connect(config_host, config_user, config_pswd);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
