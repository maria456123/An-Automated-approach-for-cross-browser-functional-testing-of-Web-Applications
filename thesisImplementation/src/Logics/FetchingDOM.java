package Logics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class FetchingDOM {		
	public static void dom() throws IOException {
		String url = ("http://localhost/addressbook/");
		//HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//		String line = null;
//		StringBuilder tmp = new StringBuilder();
//		BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
//		while ((line = in.readLine()) != null) {
//		  tmp.append(line);
//		}
		Document doc = Jsoup.connect(url).get();
		String ht=doc.outerHtml();
		Elements links = doc.select("a[href]");
	      for (Element link : links) {
	        // get the value from the href attribute
	        System.out.println("\nlink: " + link.attr("href"));
	        System.out.println("text: " + link.text());
	      }	
	      
	  	Elements navbar = doc.select("nav.r > a[href]");
	      for (Element navbars : navbar) {
	        // get the value from the href attribute
	        System.out.println("\nlink of nav: " + navbars.attr("href"));
	        System.out.println("text of nav: " + navbars.text());
	      }	
	      //Element msgbox = doc.getElementsByAttribute("msgbox");
		//System.out.println("msgbox: "+msgbox.html());
	}
public static void main(String srgs[]) throws IOException {
	dom();
}
}
