package Logics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jdom2.*;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Drivers.BrowsersWebDrivers;
import View.Index;
public class FetchingDOM {	
	static BrowsersWebDrivers bwd;
	static String FilePathToGetWebElement;
	static String TailoredFilePath;
	static Map<String, String> TestcaseLOCmap = new HashMap<>();

	

	public static Map<String, String> getTestcaseLOCmap() {
		return TestcaseLOCmap;
	}

	public static void setTestcaseLOCmap(Map<String, String> testcaseLOCmap) {
		TestcaseLOCmap = testcaseLOCmap;
	}

	static int lineNumber;
	static String LineContent;


	static WebDriver driver = BrowsersWebDrivers.getDriver();
	static String Current_dir=System.getProperty("user.dir");	
	static String my_site;
	static String content;
	static String urlOfWebApplication;
	static File folderforXmlFiles=new File(System.getProperty("user.dir")+"\\src\\XMLFiles");
	static ArrayList<String> listofChilds = new ArrayList<String>();
	public static void dom ()throws IOException, JDOMException, InterruptedException {
	
//		Document doc = Jsoup.connect(url)
//				  .data("user", "pass")
//				  .userAgent("Mozilla")
//				  .cookie("admin", "secret")
//				  .post();
//		fetcfLinksOfWebApplication(doc);
	
		//String suiteName=Index.getSelectedDirectory().getName().toString();
//		System.out.println("Suite name is "+suiteName);
//		if(suiteName.contains("claroline")) {
//			Connection.Response loginFormclarolineApplication = Jsoup.connect("http://localhost/claroline-1.11.10-1/claroline-1.11.10-1/").method(Connection.Method.GET).execute();	
//			Document doc= Jsoup.connect(url)
//					.data("cookieexists", "false")
//					.data("user", "admin")
//					.data("pass", "admin")
//					.data("submit", "Login")
//					.cookies(loginFormclarolineApplication.cookies())
//					.get();
//			setUrlOfWebApplication(doc.baseUri());
//			Node root = doc.root();
//			fetcfLinksOfWebApplication(doc);
//			String content=doc.html();
//			ArrayList<String>elems=getPageElements(content);
//			for (String elementinf : elems) {
//				System.out.println(elementinf.toString());
//			}
//		}
//		else if(suiteName.contains("addressbook")) {
		String url=TestNGListeners.getUrl();
			Connection.Response loginFormaddressbookApplication = Jsoup.connect("http://localhost/addressbook/").method(Connection.Method.GET).execute();	
			Document doc1= Jsoup.connect(url)
					.data("cookieexists", "false")
					.data("user", "admin")
					.data("pass", "secret")
					.data("submit", "Login")
					.cookies(loginFormaddressbookApplication.cookies())
					.get();
			setUrlOfWebApplication(doc1.baseUri());
			Node root = doc1.root();
			fetcfLinksOfWebApplication(doc1);
			content=doc1.toString();
			ArrayList<String>elems=getPageElements(content);
			for (String elementinf : elems) {
				System.out.println(elementinf.toString());
			}
//		}
//		else if(suiteName.contains("collabtive")) {
//			Connection.Response logincollabtiveApplication = Jsoup.connect("http://localhost/collabtive/").method(Connection.Method.GET).execute();	
//			Document doc= Jsoup.connect(url)
//					.data("cookieexists", "false")
//					.data("user", "admin")
//					.data("pass", "admin")
//					.data("submit", "Login")
//					.cookies(logincollabtiveApplication.cookies())
//					.get();
//			Node root = doc.root();
//			setUrlOfWebApplication(doc.baseUri());
//			fetcfLinksOfWebApplication(doc);
//			String content=doc.html();
//			ArrayList<String>elems=getPageElements(content);
//			for (String elementinf : elems) {
//				System.out.println(elementinf.toString());
//			}
//
//		}
		//		doc = Jsoup.parse(doc.html(), "", Parser.xmlParser());
		//		doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml); 
		//getAllElementsAttributesFromDOM(doc);
		//		String content=doc.toString();
		//		//JSoup to JDOM convert 
		//		for (Element link : links) {
		//			String li=link.attr("abs:href");
		//			my_site=trim(link.text(), 35);
		//			getXml(content,my_site);
		
		//			org.jdom2.Document docjdom = sb.build(new StringReader(doc.html()));
		//			String mycontent=docjdom.toString();
		//			XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());	
		//xout.output(docjdom.getContent(),System.out);
		//writing jdom on XML file 
		//			xout.output(docjdom.getContent(),getXml(mycontent,my_site));

	}
	//		System.out.println("*************************************************");
	//		System.out.println("DOCUMENT OF : "+ url);
	//		System.out.println("*************************************************");

	//		String selector=Extract_XpathSelector(locator);
	//		String attribute=Extract_XpathAttribute(locator);
	//		System.out.println("Selector is :"+selector+" and Attribute is :"+attribute);
	//		ArrayList<String>elems=getPageElements(content);
	//		for (String elementinf : elems) {
	//			System.out.println(elementinf.toString());
	//		}
	//		String my_site = null;
	//		for (Element link : links) {
	//			String li=link.attr("abs:href");
	//			my_site=trim(link.text(), 35);
	//			//getXml(content,my_site,attribute,selector);
	//			//String xpath=searchElementInDOM(selector,attribute);
	//			
	//		}
	//		ArrayList<ElementInfo> elementinformation=new ArrayList<ElementInfo>();
	//		elementinformation=getAllElementsAttributesFromDOM(doc);
	//		String xpath=searchElementInDOM("id","proj_1");
	//		System.out.println("New xpath is : "+xpath);
	//				int i=0;	
	//				for (ElementInfo elementinf : elementinformation) {
	//					if(i<=elementinformation.size()) {
	//						String tagName=elementinf.getTagName();
	//						String elementText=elementinf.getText();
	//						String attributeName=elementinf.getAttributes().get(i);
	//						String attributeValue=elementinf.getValues().get(i);
	//						System.out.println("TagName:"+tagName.toString());
	//						System.out.println("Text:"+elementText);
	//						System.out.println("AttributeName:"+attributeName);
	//						System.out.println("AttributeValue"+attributeValue);
	//						i++;
	//						System.out.println("****************************");
	//					}
	//				}
	//		ArrayList all = new ArrayList();
	//		Elements elements = doc.body().select("*");
	//		for (Element element : elements) {
	//			Elements element1;
	//			element1 = element.getElementsByAttributeValueMatching(selector,attribute);
	//			System.out.println(element1.get(0).nodeName());
	//			for (Element element2 : element1) {
	//				StringBuilder path = new StringBuilder(element2.nodeName());
	//				System.out.println(path);
	//				String value = element2.ownText();
	//				System.out.println(value);
	//				Elements p_el = element2.parents();
	//				String node = "";
	//				int count = 0;
	//				for (Element el : p_el) {
	//					path.insert(0, el.nodeName() + '/');
	//					System.out.println("parents: "+path);
	//				}
	//				all.add(path + "[text()='" + value + "']");
	//				if (value.isEmpty()) {
	//					System.out.println("path: "+path + "[@" + attribute + "='" + attribute.replace("\"", "") + "']");
	//				} else {
	//					System.out.println("path: "+path + "[text()='" + value + "']");
	//				}
	//
	//			}
	//
	//		}


	//System.out.println("sb line print hojanu chatie"+xpath);
	//		if(xp.contains("//*")) {
	//			System.out.println("Absolute XPath");
	//			String abs_xpath=getAbsoluteXPath(driver.findElement(By.xpath(xp)));	
	//			System.out.println("Absolute xpath of web element: "+ abs_xpath);
	//		}

	//	Elements mat=doc.getElementsByAttributeValue("class", xp);
	//		System.out.println("*************************************************");
	//		System.out.println("Links in Web Application");
	//		System.out.println("*************************************************");

	//JSoup to JDOM convert 
	//		SAXBuilder sb = new SAXBuilder();
	//		org.jdom2.Document docjdom = sb.build(new StringReader(doc.html()));
	//		mycontent=docjdom.toString();
	//XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());	
	//writing jdom on XML file 
	//xout.output(docjdom.getContent(),getXml(mycontent,my_site));
	//org.w3c.dom.Document docw3c = (org.w3c.dom.Document) sb.build(new StringReader(doc.html()));
	//XMLOutputter xout1 = new XMLOutputter(Format.getPrettyFormat());	
	//writing on console jdom
	//xout.output(docjdom, System.out);
	//System.out.println("DOM of "+ url + "has been loaded....");
	//searchElementInDOM(css_selector);
	//		String xp=Extract_xpath(Line).toString();
	//		System.out.println("path is:"+xp);
	//		//searchElementInDOM(xp,doc);
	//		if(xp.contains("//*")) {
	//			System.out.println("Absolute XPath");
	//			String abs_xpath=getAbsoluteXPath(driver.findElement(By.id("search-az")));	
	//			System.out.println("Absolute xpath of web element: "+ abs_xpath);
	//		}
	//		searchElementInDOM(css_selector,doc);

	//		Elements mat=doc.getElementsByAttributeValue("class", css_selector);
	//		String matw2=doc.getElementsMatchingText(css_selector).text();
	//		System.out.println("matched element"+matw2);
	////		Elements matching_ele=doc.getElementsMatchingText(css_selector);
	////		for (Element el : matching_ele) {
	////			System.out.println("matched ele"+el);
	////		}
	//		System.out.println("css attribute value get"+mat);
	//		String b=mat.attr("name");
	//		Element alter=doc.closest(css_selector);
	//System.out.println("Alternative of css loactor : "+alter);


	//}

	public static  String Construct_Xpath_WebElement(String line) throws IOException {
		String locator="";
		if(line.contains("By.xpath")) {
			String Information=Get_Line_Info();
			System.out.println("All Information is:"+Information);

		}
		else if(line.contains("By.name")) {


		}
		else if(line.contains("By.cssSelector")) {
			String css=Extract_css_locator(line);
			locator=css;

		}
		else if(line.contains("By.id")) {
			String id=Extract_css_locator(line);
			locator=id;
		}
		return locator;


	}
	public static Elements recurseOverElements(Elements elementList, Elements allChildElemets){
		if(elementList.size() == 0)
			return allChildElemets;
		for (org.jsoup.nodes.Element element : elementList) {
			recurseOverElements(element.children(), allChildElemets);
			allChildElemets.add(element);
		}
		return allChildElemets;
	}
		public static String getAbsoluteXPath(WebElement element)
		{
			return (String) ((JavascriptExecutor) driver).executeScript(
					"function absoluteXPath(element) {"+
							"var comp, comps = [];"+
							"var parent = null;"+
							"var xpath = '';"+
							"var getPos = function(element) {"+
							"var position = 1, curNode;"+
							"if (element.nodeType == Node.ATTRIBUTE_NODE) {"+
							"return null;"+
							"}"+
							"for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {"+
							"if (curNode.nodeName == element.nodeName) {"+
							"++position;"+
							"}"+
							"}"+
							"return position;"+
							"};"+
							"if (element instanceof Document) {"+
							"return '/';"+
							"}"+
	
		                    "for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {"+
		                    "comp = comps[comps.length] = {};"+
		                    "switch (element.nodeType) {"+
		                    "case Node.TEXT_NODE:"+
		                    "comp.name = 'text()';"+
		                    "break;"+
		                    "case Node.ATTRIBUTE_NODE:"+
		                    "comp.name = '@' + element.nodeName;"+
		                    "break;"+
		                    "case Node.PROCESSING_INSTRUCTION_NODE:"+
		                    "comp.name = 'processing-instruction()';"+
		                    "break;"+
		                    "case Node.COMMENT_NODE:"+
		                    "comp.name = 'comment()';"+
		                    "break;"+
		                    "case Node.ELEMENT_NODE:"+
		                    "comp.name = element.nodeName;"+
		                    "break;"+
		                    "}"+
		                    "comp.position = getPos(element);"+
		                    "}"+
	
		                    "for (var i = comps.length - 1; i >= 0; i--) {"+
		                    "comp = comps[i];"+
		                    "xpath += '/' + comp.name.toLowerCase();"+
		                    "if (comp.position !== null) {"+
		                    "xpath += '[' + comp.position + ']';"+
		                    "}"+
		                    "}"+
	
		                    "return xpath;"+
	
		                    "} return absoluteXPath(arguments[0]);", element);
		}
	// Java implementation of recursive Levenshtein distance 
	// calculation 
	//	static int compute_Levenshtein_distance(String str1, 
	//			String str2) 
	//	{  
	//		if (str1.isEmpty()) 
	//		{ 
	//			return str2.length(); 
	//		} 
	//		if (str2.isEmpty()) 
	//		{ 
	//			return str1.length(); 
	//		} 
	//		int replace = compute_Levenshtein_distance( 
	//				str1.substring(1), str2.substring(1)) 
	//				+ NumOfReplacement(str1.charAt(0),str2.charAt(0)); 
	//		int insert = compute_Levenshtein_distance( 
	//				str1, str2.substring(1))+ 1; 
	//		int delete = compute_Levenshtein_distance( 
	//				str1.substring(1), str2)+ 1; 
	//		return minm_edits(replace, insert, delete); 
	//	} 
	//	static int NumOfReplacement(char c1, char c2) 
	//	{ 
	//		return c1 == c2 ? 0 : 1; 
	//	} 
	//
	//	static int minm_edits(int... nums) 
	//	{ 
	//		return Arrays.stream(nums).min().orElse( 
	//				Integer.MAX_VALUE); 
	//	} 
	//line information
	public static String Get_Line_Info() throws IOException {
		String line_info="";
		String actual_path="";
		String browser="";
		String tcname="";
		if(TestNGListeners.getSuiteName().equalsIgnoreCase("Test Suite")) {
			browser=TestNGListeners.getBrowserName();
			TestNGListeners tl=new TestNGListeners();
			tcname=TestNGListeners.getTestcase_name();
			line_info=TestNGListeners.getLine();
		}
		else {
			browser=TestNGListeners.getBrowserName();
			TailoredTestNGListeners tl=new TailoredTestNGListeners();
			tcname=tl.getTestcase_name();
			line_info=TailoredTestNGListeners.getLine();
		}
	
		System.out.println(line_info);
		String[] Split_line=line_info.split("\\(");
		String path=Split_line[0];
		path=path.replace(".", "\\");
		int last_index=path.lastIndexOf("\\");
		path=path.substring(0, last_index);
		actual_path="\\"+path;
		String wd=System.getProperty("user.dir");			 
		actual_path=actual_path+".java";
		actual_path=actual_path.replace("null\\","");
		actual_path=actual_path.trim();
		String[] actual_line=Split_line[1].split(":");
		String[] actual=actual_line[1].split("\\)");
		int actual_line_number=Integer.parseInt(actual[0]);	
		setLineNumber(actual_line_number);
		System.out.println("Actual Line NUMEBR IS: "+actual_line_number);
		System.out.println("Actual File path IS: " +wd+"\\src\\"+actual_path);
		String filepath=wd+"\\src\\"+actual_path;
		setFilePathToGetWebElement(filepath);
		String tailoredPath=filepath.replace("InputSuite", "TailoredSuite"+browser);
		setTailoredFilePath(tailoredPath);
		System.out.println("Tailored Path is :"+ tailoredPath);
		String Line = Files.readAllLines(Paths.get(wd+"\\src\\"+actual_path)).get(actual_line_number-1);
		System.out.println("LINE of file is :"+Line);
		setLineContent(Line);
		Map<String, String> TestcaseLOC = new HashMap<>();
		 TestcaseLOC.put(tcname, Line);
		 setTestcaseLOCmap(TestcaseLOC);
			try {
				String FailedDetailsFile=System.getProperty("user.dir")+"\\src\\FailedTestsDetails\\";
				FileWriter myWriter = new FileWriter(FailedDetailsFile,true);
				myWriter.append(tcname+"--"+Line+"--"+actual_line_number+"--"+tailoredPath);
				myWriter.append("\n");
				myWriter.close();
			}
			catch (IOException e) {
				System.out.println("An error occurred.");
			}
		return Line;	
	}
	public static String Extract_xpath(String Line) throws IOException {
		String xpath = null;
		Pattern p = Pattern.compile("\".*\"");
		Matcher m = p.matcher(Line);
		while (m.find()) {
			xpath=m.group(0);
			if(xpath.startsWith("\"") && xpath.endsWith("\""))
			{
				xpath= xpath.substring(1, xpath.length()-1).trim();
			}
		}
		return xpath;
	}
	public static String Extract_XpathAttribute(String xp) throws IOException {
		String[] attribute = null;
		attribute=xp.split("@");
		attribute=attribute[1].split("\\]");
		attribute=attribute[0].split("=\"");
		attribute=attribute[0].split("=");
		String att=attribute[1];
		if(att.startsWith("\\\"") && att.endsWith("\\\""))
		{
			att= att.substring(2, att.length()-2);		
			return att;

		}	
		return att;
	}
		public static String Extract_XpathSelector(String xp) throws IOException {
			String[] attribute = null;
			attribute=xp.split("@");
			attribute=attribute[1].split("\\]");
			attribute=attribute[0].split("=\"");
			attribute=attribute[0].split("=");
			String selector=attribute[0];		
			return selector;
		}
	public static String Extract_css_locator(String Line) throws IOException {

		String css = null;
		Pattern p = Pattern.compile("\".*\"");
		Matcher m = p.matcher(Line);
		while (m.find()) {
			css=m.group(0);
			if(css.startsWith("\"") && css.endsWith("\""))
			{
				css= css.substring(1, css.length()-1);
				System.out.println("CSS locator: "+css);
			}
		}
		return css;
	}
	public static void css_heuristic(Document docjdom) throws IOException {
		//		String css_selector=Extract_css_locator() ;
		//		System.out.println("CSS_selector: "+ css_selector);
		//		int Edit_distance=compute_Levenshtein_distance(docjdom.toString(),css_selector);
		//		System.out.println("Edit Distance:"+ Edit_distance);
	}
	public static Document convert(org.jsoup.nodes.Document in) {
		return (Document) (new W3CDom().fromJsoup(in));
	}
	public static OutputStream getXml(String mycontent,String path_name) {
		FileOutputStream fos = null;
		try {
			//Specify the file path here
			File file = new File(Current_dir +"\\src\\XMLFiles\\"+path_name+".xml");
			fos = new FileOutputStream(file,false);

			if (!file.exists()) {
				file.createNewFile();
			}
			else if(file.exists()){
				boolean deleted= file.delete();
				if(deleted==true) {
					System.out.println("Deleting already existed file with same name....");
					file.createNewFile();
					System.out.println("NewFile created...." + file.getName());
				}
			}
			byte[] bytesArray = mycontent.getBytes();		
			fos.write(bytesArray);
			fos.flush();
			System.out.println("File "+file.getName()+" has been written Successfully...");
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		return fos;
	}
		public static String searchElementInDOM(String attribute,String attribute_text) throws IOException {
			int lineNumber = 0;
			String data="";
			File folder=new File(Current_dir+"\\src\\XMLFiles");
			ArrayList<File> files_list = new ArrayList<File>();
			files_list=ListFilesInDom(folder);
			System.out.println(files_list);
			for (File fileEntry : files_list) {
				File file=new File(Current_dir+"\\src\\XMLFiles\\"+fileEntry.getName());
				Document doc = null;
				try {
					doc = Jsoup.parse(file, "UTF-8");
					System.out.println(file.getName()+" has been parsed by JSoup...");
				} catch (IOException e) {
					e.printStackTrace();
				}
				Elements elements = doc.body().select("*");
				ArrayList all = new ArrayList();
				for (Element element : elements) {
					Elements element1;
					element1 = element.getElementsByAttributeValueMatching(attribute, attribute_text);
					for (Element element2 : element1) {
						StringBuilder path = new StringBuilder(element2.nodeName());
						System.out.println("builder path is : "+path);
						String value = element2.ownText().toString();
						System.out.println("value is :"+value);
						Elements p_el = element2.parents();
						String node = "";
						int count = 0;
						for (Element el : p_el) {
							path.insert(0, el.nodeName() + '/');
							System.out.println("parents path is : "+path);
						}
						all.add(path + "[text()='" + value + "']");
						System.out.println("all at index 0: "+all.get(0));
						if (value.isEmpty()) {
							System.out.println("value is empty Xpath is :  "+path + "[@" + attribute + "='" + attribute_text + "']");
							return path + "[@" + attribute + "='" + attribute_text + "']";
						} else {
							System.out.println("value is not empty Xpath is : "+path + "[text()='" + value + "']");
							return path + "[text()='" + value + "']";
						}
					}
				}
	
			}
			
			return "";
		} 
		public static ArrayList<File>  ListFilesInDom(File folder) {
			ArrayList<File> files_list =  new ArrayList<File>();
			for (File fileEntry : folder.listFiles()) {
				if (fileEntry.isDirectory()) {
					ListFilesInDom(fileEntry);
				} else {
					files_list.add(fileEntry);
				}
			}
			return files_list;
		}
		public String generateCssselector(Element e) {
			//css=<HTML tag><.><Value of Class attribute>
			String css;
			e.tagName();
			e.attr("[attr=value]");
			return css=e.tagName()+"."+e.attr("[attr=value]");
		}
	
	public static ArrayList<String> getPageElements(String content) {
		listofChilds = new ArrayList<String>();
		System.out.println("----------------------------");
		String data = content;
		ArrayList<String> childList = new ArrayList<String>();
		try {
			Document document = Jsoup.parse(content);
			Elements bodyElements = document.body().select("body");
			Element oneElement = bodyElements.first();
			List<org.jsoup.nodes.Node> childNodes = oneElement.childNodes();
			for (int i = 0; i < childNodes.size(); i++) {
				org.jsoup.nodes.Node node = childNodes.get(i);
				calculateNodeChilds(node);
			}

			for (int i = 0; i < listofChilds.size(); i++) {

				System.out.println("");
				System.out.println("" + listofChilds.get(i));
				System.out.println("");

			}
			System.out.println("-----------------------------------------------");

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return listofChilds;

	}
	public static ArrayList<String> calculateNodeChilds(org.jsoup.nodes.Node node) {

		List<org.jsoup.nodes.Node> childs = node.childNodes();
		if ((childs != null && childs.size() > 0)) {
			for (int j = 0; j < childs.size(); j++) {
				if (childs.get(j).toString().startsWith("<div") || childs.get(j).toString().startsWith("<form")
						|| childs.get(j).toString().startsWith("<li") 
						|| childs.get(j).toString().startsWith("<ul")
						|| childs.get(j).toString().startsWith("<table")
						|| childs.get(j).toString().startsWith("<datalist")
						|| childs.get(j).toString().startsWith("<select")
						|| childs.get(j).toString().startsWith("<label")
						|| childs.get(j).toString().startsWith("<thead") || childs.get(j).toString().startsWith("<tr")
						|| childs.get(j).toString().startsWith("<thead") | childs.get(j).toString().startsWith("<tbody")
						|| childs.get(j).toString().startsWith("<fieldset")
						|| childs.get(j).toString().startsWith("<tbody")) {
					org.jsoup.nodes.Node child = childs.get(j);

					if (childs.get(j).toString().startsWith("<datalist")
							|| childs.get(j).toString().startsWith("<select")) {
						listofChilds.add(childs.get(j).toString());
					}
					calculateNodeChilds(childs.get(j));
				} else {
					if (!childs.get(j).toString().matches("") && !childs.get(j).toString().contains("<br")
							&& childs.get(j).toString().length() > 5 && childs.get(j).toString().startsWith("<")) {
						if (childs.get(j).toString().startsWith("<a") || childs.get(j).toString().startsWith("<button")
								|| childs.get(j).toString().startsWith("<input")
								|| childs.get(j).toString().startsWith("<h")
								|| childs.get(j).toString().startsWith("<img")
								|| childs.get(j).toString().startsWith("<textarea")
								|| childs.get(j).toString().startsWith("<select")
								|| childs.get(j).toString().startsWith("<option")
								|| childs.get(j).toString().startsWith("<p")
								|| childs.get(j).toString().startsWith("<label")
								|| childs.get(j).toString().startsWith("<ul")
								|| childs.get(j).toString().startsWith("<li")
								|| childs.get(j).toString().startsWith("<legend"))
							listofChilds.add(childs.get(j).toString());
					}

				}
			}

		} else {
			if (!node.toString().matches("") && !node.toString().contains("<br") && node.toString().length() > 5
					&& node.toString().startsWith("<")) {
				if (!node.toString().matches("") && !node.toString().contains("<br") && node.toString().length() > 5
						&& node.toString().startsWith("<")) {
					if (node.toString().startsWith("<a") || node.toString().startsWith("<button")
							|| node.toString().startsWith("<input") || node.toString().startsWith("<h")
							|| node.toString().startsWith("<img") || node.toString().startsWith("<textarea")
							|| node.toString().startsWith("<select") || node.toString().startsWith("<option")
							|| node.toString().startsWith("<p") || node.toString().startsWith("<label")
							|| node.toString().startsWith("<legend"))
						listofChilds.add(node.toString());
				}
			}
		}

		return listofChilds;
	}	
	public static void getAllElementsAttributesFromDOM(Document doc) {
		Elements allElementsInDom = new Elements();
		recurseOverElements(doc.getAllElements(), allElementsInDom);
		ArrayList<ElementInfo> elementinformation=new ArrayList<ElementInfo>();
		ArrayList<String> attributes=new ArrayList<String>();
		String nodeName = "";
		ArrayList<String> val=new ArrayList<String>();
		for (org.jsoup.nodes.Element element : allElementsInDom) {
			nodeName=element.nodeName().toString();
			attributes.add(element.attributes().get("name").toString());
			val.add(element.attributes().get("value").toString());
			elementinformation.add(new ElementInfo(element.tagName(),attributes,element.text(),val));
		}
		int i=0;	
		for (ElementInfo elementinf : elementinformation) {
			if(i<=elementinformation.size()) {
				String tagName=elementinf.getTagName();
				String elementText=elementinf.getText();
				String attributeName=elementinf.getAttributes().get(i);
				String attributeValue=elementinf.getValues().get(i);
				//						System.out.println("TagName:"+tagName.toString());
				//						System.out.println("Text:"+elementText);
				System.out.println("Attribute name and attribute value");
				System.out.println("AttributeName:"+attributeName);
				System.out.println("AttributeValue"+attributeValue);
				i++;
				System.out.println("****************************");
			}
		}
	}
	public static String xpathFinderFromFile(String attribute, String attribute_text, File file) {

		Document doc = null;
		try {
			doc = Jsoup.parse(file, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements elements = doc.body().select("*");
		ArrayList all = new ArrayList();
		for (Element element : elements) {

			Elements element1;
			element1 = element.getElementsByAttributeValueMatching(attribute, attribute_text);
			for (Element element2 : element1) {

				StringBuilder path = new StringBuilder(element2.nodeName());
				String value = element2.ownText();
				Elements p_el = element2.parents();
				String node = "";
				int count = 0;
				for (Element el : p_el) {
					path.insert(0, el.nodeName() + '/');
				}
				all.add(path + "[text()='" + value + "']");
				if (value.isEmpty()) {
					return path + "[@" + attribute + "='" + attribute_text + "']";
				} else {
					return path + "[text()='" + value + "']";
				}

			}

		}

		return "";

	}
	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}
	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width-1) + ".";
		else
			return s;
	}
	public static void fetcfLinksOfWebApplication(Document doc) {
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			String li=link.attr("abs:href");
			print(" * a:", link.attr("abs:href"), trim(link.text(), 35));
			doc = Jsoup.parse(doc.html(), "", Parser.xmlParser());
			doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml); 
			String content=doc.toString();
			my_site=trim(link.text(), 35);
			getXml(content,my_site);
			System.out.println("Base uri: "+doc.baseUri());
		}
	}
	public static String getUrlOfWebApplication() {
		return urlOfWebApplication;
	}
	public static void setUrlOfWebApplication(String urlOfWebApplication) {
		FetchingDOM.urlOfWebApplication = urlOfWebApplication;
	}
private static void getSpecificElement(String content, String element) {	
	Document document = Jsoup.parse(content);
	ArrayList<String> elementList = new ArrayList<String>();
	System.out.println("----------------------------");
	String data = content;
	ArrayList<String> childList = new ArrayList<String>();
	Elements bodyElements = document.body().select(element);
	Element oneElement = bodyElements.first();
	List<org.jsoup.nodes.Node> childNodes = oneElement.childNodes();
	for (int i = 0; i < bodyElements.size(); i++) {
		// org.jsoup.nodes.Node node = childNodes.get(i);
		// calculateNodeChilds(node);
		elementList.add(bodyElements.get(i).toString());
}
}
public static String getFilePathToGetWebElement() {
	return FilePathToGetWebElement;
}

public static void setFilePathToGetWebElement(String filePathToGetWebElement) {
	FilePathToGetWebElement = filePathToGetWebElement;
}
public static int getLineNumber() {
	return lineNumber;
}

public static void setLineNumber(int lineNumber) {
	FetchingDOM.lineNumber = lineNumber;
}
public static String getLineContent() {
	return LineContent;
}
public static void setLineContent(String lineContent) {
	LineContent = lineContent;
}
public static String getTailoredFilePath() {
	return TailoredFilePath;
}

public static void setTailoredFilePath(String tailoredFilePath) {
	TailoredFilePath = tailoredFilePath;
}
//public static void main(String args[]) throws IOException, JDOMException, InterruptedException 
//{ 
//	dom();
//} 

}
