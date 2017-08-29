import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetFromURL {
	public static void main(String[] args) throws Exception {
		URL remote = new URL("http://w1.weather.gov/xml/current_obs/KMGW.xml");
		Scanner web = new Scanner(remote.openStream());
		
		web.useDelimiter("\\Z");
		String data = web.next();
		web.close();
		
		String location = "Unknown";
		String ftemp = "Unknown";
		
		Pattern p = Pattern.compile("<location>(.*)</location>");
		Matcher m = p.matcher(data);
		if (m.find()) {
			location = m.group(1);
			
			p = Pattern.compile("<temp_f>(.*)</temp_f>");
			m = p.matcher(data);
			if (m.find()) ftemp = m.group(1);
		}
		
		System.out.printf("Temperature at %s: %s°F\n", location, ftemp);
	}
}
