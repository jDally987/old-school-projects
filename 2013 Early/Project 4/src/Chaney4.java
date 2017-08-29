/*
 * John Chaney
 * Project 4: Tech
 * CS 110 5/3/13 Spring 2013
 * Bonus: no
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URL;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Chaney4 {

	public static void main(String[] args) throws Exception {
		Scanner parse = new Scanner(getConfig());
		// parse file and save each tag using skip() and specifying the tag names for each
		String acct = parse.skip("acct\\s").next();
		parse.nextLine();
		String pswd = parse.skip("pswd\\s").next();
		parse.nextLine();
		String dest = parse.skip("dest\\s").next();
		parse.nextLine();
		String code = parse.skip("code\\s").next();
		
		String date = getWebData(code);
		// execute initial SMS update command
		sendUpdate(date, acct, pswd, dest, code);
		
		// now do log file business, and check if we need to send update again
		if (checkLogFile(date)){
			sendUpdate(date, acct, pswd, dest, code);
		} else System.exit(0); // if not, end quietly
		
		
	}
	
	/**
	 * Finds & returns the config.txt file in the root directory. If it does not exist,
	 * the method says so, and exits the program.
	 * @return a file object of config.txt
	 */
	public static File getConfig() {
		File file;
		file = new File("config.txt");
		if (!file.isFile()){
			System.out.println("Sorry, config file does not exist or is invalid. Goodbye.");
			System.exit(1);
		}
		return file;
	}
	
	
	/**
	 * Retrieves & parses Prof. Reaser's website to determine if any assignments
	 * have been updated, according to the last modified date.
	 * @param code - the class code to be used in the URL (cs110, cs111, etc.)
	 * @return date - the date as a String
	 * @throws IOException
	 */
	public static String getWebData(String code) throws IOException {
		URL url;
		String date=null;
		try {
			url = new URL("http://csee.wvu.edu/~rreaser/"+code+"/projects/?C=M;O=D");
			Scanner pgscn = new Scanner(url.openStream());
			String page = pgscn.useDelimiter("\\Z").next();
			
			// search for the FIRST last modified date, in date format for the regex
			Pattern p = Pattern.compile("(\\d{2})-(\\w{3})-(\\d{4})\\s(\\d{2}):(\\d{2})");
			Matcher m = p.matcher(page);
			m.find();
			// store it as "date", of course
			date = m.group();
			
		} catch (Exception e) {
			System.out.print("It appears the class code (specified in config.txt) is invalid. Goodbye.");
			System.exit(1);
		}
		return date;
	}
	
	
	/**
	 * Checks for a log file, and updates it to include the latest modified file's date.
	 * @param date - last modified date of newest file
	 * @throws IOException 
	 */
	public static boolean checkLogFile(String date) throws IOException {
		boolean newer = false, exist = false;
		File log = new File("log.txt");
		
		// creates file if it didn't exist already; sets exist = true if it did
		if (!log.createNewFile()) exist = true;
		
		BufferedReader read = new BufferedReader(new FileReader(log));
		FileWriter write = new FileWriter(log, true);
		
		if (exist){
			// skips to end of file to store the last line (the most recent date)
			String oldDate=null, last;
			while ((last = read.readLine()) != null){
				oldDate = last;
			}
			
			// IMPORTANT PART
			// checks if the current date is newer than the last line in the log file
			if (!oldDate.equals(date)){
				newer = true;
				write.write(date + System.getProperty("line.separator"));
			}
		}else {
			// file didn't exist before, so write to it its first date
			write.write(date + System.getProperty("line.separator"));
		}
		
		read.close();
		write.close();
		return newer;
	}
	
	
	/**
	 * Sends a single update via SMS (or email) to the specified destination.
	 * @param date - date of last modified project file
	 * @param acct - the user's account to send from (email address)
	 * @param pswd - user's password
	 * @param dest - destination address
	 * @param code - class code
	 * @throws Exception
	 */
	public static void sendUpdate(String date, String acct, String pswd, String dest, String code) throws Exception {
		Properties prop = new Properties();
		
		String subject = "New Update for "+ code;
		String body = "An update has been found for class "+code+". Update occurred on: "+date+". Please check Prof. Reaser's website.";
		
		String host = "exsmtp.wvu.edu";
		String port = "465";
		
		String user = acct.substring(0, acct.length()-12);
		
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.user", user);
		
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.debug", "false");
		
		prop.put("mail.smtp.socketFactory.port", port);
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.socketFactory.fallback", "false");
		
		Session session = Session.getDefaultInstance(prop, null);
		session.setDebug(false);
		
		MimeMessage message = new MimeMessage(session);
		message.setText(body);
		message.setSubject(subject);
		message.setFrom(new InternetAddress(acct));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
		message.saveChanges();
		
		Transport transport = session.getTransport("smtp");
		transport.connect(host, user, pswd);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
}
