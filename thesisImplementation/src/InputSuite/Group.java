package InputSuite;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Group {
	
	

	String group_header;
	String group_footer;
	String group_name;
	
    public Group(String group_name, String group_header, String group_footer)
	{
	this.group_name=group_name;
	this.group_header=group_header;
	this.group_footer=group_footer;		
	}

	public Group() {
		// TODO Auto-generated constructor stub
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getGroup_header() {
		return group_header;
	}

	public void setGroup_header(String group_header) {
		this.group_header = group_header;
	}

	public String getGroup_footer() {
		return group_footer;
	}

	public void setGroup_footer(String group_footer) {
		this.group_footer = group_footer;}
	
	ArrayList<Group> groupList = new ArrayList<>();
	String filePath = "C:\\Users\\Haier\\Downloads\\group.xlsx";
	
	
public ArrayList<Group> readGroupData()
{
try {
	  FileInputStream fs = new FileInputStream(filePath);
   HSSFWorkbook wb = new HSSFWorkbook(fs);
   HSSFSheet sheet = wb.getSheetAt(0);
   HSSFRow row;
   HSSFCell cell;

   int rows; // No of rows
   rows = sheet.getPhysicalNumberOfRows();

   int cols = 0; // No of columns
   int tmp = 0;

   // This trick ensures that we get the data properly even if it doesn't start from first few rows
   for(int i = 0; i < 10 || i < rows; i++) {
       row = sheet.getRow(i);
       if(row != null) {
           tmp = sheet.getRow(i).getPhysicalNumberOfCells();
           if(tmp > cols) cols = tmp;
       }
   }
  for(int r = 0; r < rows; r++) {
       row = sheet.getRow(r);
       if(row != null && r!=0) {
    	   String group_name="";
    		String group_header="";
    		String group_footer="";
    		
           for(int c = 0; c < cols; c++) {
        	   System.out.println("Coulms Size: - "+cols);
               cell = row.getCell((short)c);
               if(cell != null) {
                  if(c==0)
                  {
                	  group_name = String.valueOf(cell);
                  }else if(c==1)
                  {
                	  group_header = String.valueOf(cell);
                  }else if(c==2)
                  {
                	  group_footer = String.valueOf(cell);
                  }
               }
          }
                  
          
          groupList.add(new Group(group_name,group_header, group_footer));
           }
       }}
  
  
 catch(Exception ioe) {
   ioe.printStackTrace();
}
return groupList;
}

public List<String> getChildren() {
	// TODO Auto-generated method stub
	return null;
}
}
