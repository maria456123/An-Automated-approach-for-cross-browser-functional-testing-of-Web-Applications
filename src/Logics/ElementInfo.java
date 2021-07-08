package Logics;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

class ElementInfo
{
	String tagName = new String();
    ArrayList<String>attributes    = new ArrayList<String>();
    ArrayList<String>values   = new ArrayList<String>();
	LinkedHashSet<Set<String>> setAttributes = new LinkedHashSet<Set<String>>();
    int			position      = -1;
    int			positionStar  = -1;
    String		     text	  = new String();
	
	public ElementInfo() {
		this.tagName = "";
		this.attributes = null;
		this.text = "";
		this.values=null;
	}
	public ElementInfo(String tagName, ArrayList<String> attributes, String text,ArrayList<String> values) {
		super();
		this.tagName = tagName;
		this.attributes = attributes;
		this.text = text;
		this.values=values;
	}
	

    public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public ArrayList<String> getAttributes() {
		return attributes;
	}
	public void setAttributes(ArrayList<String> attributes) {
		this.attributes = attributes;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	  public ArrayList<String> getValues() {
			return values;
		}
		public void setValues(ArrayList<String> values) {
			this.values = values;
		}
	
}
