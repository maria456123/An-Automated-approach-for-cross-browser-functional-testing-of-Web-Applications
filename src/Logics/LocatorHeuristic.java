//package Logics;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//public class LocatorHeuristic {
//	static ArrayList<String> listofChilds = new ArrayList<String>();
//	public static void XpathTokenizer() throws IOException {
//		System.out.println("Locator Heuristic called....");
//		String Current_dir=System.getProperty("user.dir");
//		String Line=FetchingDOM.Get_Line_Info();
//		String xpath=Extract_xpath(Line);
//		if(xpath.contains("@")) {
//			String att= Extract_XpathAttribute(xpath);
//			String sel= Extract_XpathSelector(xpath);
//			TestNGListeners.getTestcase_name();		
//			File domfile=new File(Current_dir +"\\src\\XMLFiles"+TestNGListeners.getBrowserName()+"\\"+TestNGListeners.getTestcase_name()+".xml");
//			String NewXpath=xpathFinderFromFile(sel, att, domfile);
//			System.out.println("New XPath is:"+NewXpath);
//		}
//
//	}
//	public static String xpathFinderFromFile(String attribute, String attribute_text, File file) {
//
//		Document doc = null;
//		try {
//			doc = Jsoup.parse(file, "UTF-8");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Elements elements = doc.body().select("*");
//		ArrayList all = new ArrayList();
//		for (Element element : elements) {
//			Elements element1;
//			element1 = element.getElementsByAttributeValueMatching(attribute, attribute_text);
//			for (Element element2 : element1) {
//				StringBuilder path = new StringBuilder(element2.nodeName());
//				int childSize=element2.childNodeSize();
//				System.out.println("childnodes"+childSize);
//				String value = element2.ownText();
//				System.out.println("Value is :"+value);
//				Elements p_el = element2.parents();
//				String node = "";
//				int count = 0;
//				for (Element el : p_el) {
//					path.insert(0, el.nodeName() + '/');
//				}
//				all.add(path + "[text()='" + value + "']");
//				if (value.isEmpty()) {
//					return path + "[@" + attribute + "='" + attribute_text.replace("\"", "") + "']";
//				} else {
//					return path + "[text()='" + value + "']";
//				}
//
//			}
//
//		}
//
//		return "";
//
//	}
//	public static String Extract_xpath(String Line) throws IOException {
//		String xpath = null;
//		Pattern p = Pattern.compile("\".*\"");
//		Matcher m = p.matcher(Line);
//		while (m.find()) {
//			xpath=m.group(0);
//			if(xpath.startsWith("\"") && xpath.endsWith("\""))
//			{
//				xpath= xpath.substring(1, xpath.length()-1).trim();
//			}
//		}
//		return xpath;
//	}
//
//	public static String Extract_XpathAttribute(String xp) throws IOException {
//		String[] attribute = null;
//		attribute=xp.split("@");
//		attribute=attribute[1].split("\\]");
//		attribute=attribute[0].split("=\"");
//		attribute=attribute[0].split("=");
//		String att=attribute[1];
//		if(att.startsWith("\\\"") && att.endsWith("\\\""))
//		{
//			att= att.substring(2, att.length()-2);		
//			return att;
//
//		}	
//		return att;
//	}
//	public static ArrayList<String> getSpecificElement(String content, String element) {	
//		Document document = Jsoup.parse(content);
//		ArrayList<String> elementList = new ArrayList<String>();
//		System.out.println("----------------------------");
//		String data = content;
//		Elements bodyElements = document.body().select(element);
//		Element oneElement = bodyElements.first();
//		List<org.jsoup.nodes.Node> childNodes = oneElement.childNodes();
//		for (int i = 0; i < bodyElements.size(); i++) {
//			elementList.add(bodyElements.get(i).toString());
//	}
//		return elementList;
//	}
//	public static ArrayList<String> getPageElements(String content) {
//		listofChilds = new ArrayList<String>();
//		System.out.println("----------------------------");
//		String data = content;
//		ArrayList<String> childList = new ArrayList<String>();
//		try {
//			Document document = Jsoup.parse(content);
//			Elements bodyElements = document.body().select("body");
//			Element oneElement = bodyElements.first();
//			List<org.jsoup.nodes.Node> childNodes = oneElement.childNodes();
//			for (int i = 0; i < childNodes.size(); i++) {
//				org.jsoup.nodes.Node node = childNodes.get(i);
//				calculateNodeChilds(node);
//			}
//
//			for (int i = 0; i < listofChilds.size(); i++) {
//
//				System.out.println("");
//				System.out.println("" + listofChilds.get(i));
//				System.out.println("");
//
//			}
//			System.out.println("-----------------------------------------------");
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//
//		}
//		return listofChilds;
//
//	}
//	public static ArrayList<String> calculateNodeChilds(org.jsoup.nodes.Node node) {
//
//		List<org.jsoup.nodes.Node> childs = node.childNodes();
//		if ((childs != null && childs.size() > 0)) {
//			for (int j = 0; j < childs.size(); j++) {
//				if (childs.get(j).toString().startsWith("<div") || childs.get(j).toString().startsWith("<form")
//						|| childs.get(j).toString().startsWith("<li") 
//						|| childs.get(j).toString().startsWith("<ul")
//						|| childs.get(j).toString().startsWith("<table")
//						|| childs.get(j).toString().startsWith("<datalist")
//						|| childs.get(j).toString().startsWith("<select")
//						|| childs.get(j).toString().startsWith("<label")
//						|| childs.get(j).toString().startsWith("<thead") || childs.get(j).toString().startsWith("<tr")
//						|| childs.get(j).toString().startsWith("<thead") | childs.get(j).toString().startsWith("<tbody")
//						|| childs.get(j).toString().startsWith("<fieldset")
//						|| childs.get(j).toString().startsWith("<tbody")) {
//					org.jsoup.nodes.Node child = childs.get(j);
//
//					if (childs.get(j).toString().startsWith("<datalist")
//							|| childs.get(j).toString().startsWith("<select")) {
//						listofChilds.add(childs.get(j).toString());
//					}
//					calculateNodeChilds(childs.get(j));
//				} else {
//					if (!childs.get(j).toString().matches("") && !childs.get(j).toString().contains("<br")
//							&& childs.get(j).toString().length() > 5 && childs.get(j).toString().startsWith("<")) {
//						if (childs.get(j).toString().startsWith("<a") || childs.get(j).toString().startsWith("<button")
//								|| childs.get(j).toString().startsWith("<input")
//								|| childs.get(j).toString().startsWith("<h")
//								|| childs.get(j).toString().startsWith("<img")
//								|| childs.get(j).toString().startsWith("<textarea")
//								|| childs.get(j).toString().startsWith("<select")
//								|| childs.get(j).toString().startsWith("<option")
//								|| childs.get(j).toString().startsWith("<p")
//								|| childs.get(j).toString().startsWith("<label")
//								|| childs.get(j).toString().startsWith("<ul")
//								|| childs.get(j).toString().startsWith("<li")
//								|| childs.get(j).toString().startsWith("<legend"))
//							listofChilds.add(childs.get(j).toString());
//					}
//
//				}
//			}
//
//		} else {
//			if (!node.toString().matches("") && !node.toString().contains("<br") && node.toString().length() > 5
//					&& node.toString().startsWith("<")) {
//				if (!node.toString().matches("") && !node.toString().contains("<br") && node.toString().length() > 5
//						&& node.toString().startsWith("<")) {
//					if (node.toString().startsWith("<a") || node.toString().startsWith("<button")
//							|| node.toString().startsWith("<input") || node.toString().startsWith("<h")
//							|| node.toString().startsWith("<img") || node.toString().startsWith("<textarea")
//							|| node.toString().startsWith("<select") || node.toString().startsWith("<option")
//							|| node.toString().startsWith("<p") || node.toString().startsWith("<label")
//							|| node.toString().startsWith("<legend"))
//						listofChilds.add(node.toString());
//				}
//			}
//		}
//
//		return listofChilds;
//	}	
//	public static String Extract_XpathSelector(String xp) throws IOException {
//		String[] attribute = null;
//		attribute=xp.split("@");
//		attribute=attribute[1].split("\\]");
//		attribute=attribute[0].split("=\"");
//		attribute=attribute[0].split("=");
//		String selector=attribute[0];		
//		return selector;
//	}
//	
//
//}
