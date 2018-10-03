package parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import model.*;



public class XMLParser {
	
	public Report getReport() throws ParserConfigurationException, SAXException, IOException{
		
		File f = new File("src/resourses/settings.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(f);
        Element root = document.getDocumentElement();
        Report report = new Report();
        
        Element page = (Element) root.getElementsByTagName("page").item(0);   
        Element width = (Element) page.getElementsByTagName("width").item(0);
        report.setPageWidth(Integer.parseInt(width.getTextContent()));
         
        Element height = (Element) page.getElementsByTagName("height").item(0);
        report.setPageHeight(Integer.parseInt(height.getTextContent()));
        
        NodeList columns = (NodeList)document.getElementsByTagName("column");
        Element columnsElement = (Element) root.getElementsByTagName("columns").item(0);
      
        ArrayList<Column> columnsList = new ArrayList<Column>();
        for (int i = 0; i < columns.getLength(); i++){
        	
        	Column column = new Column();
        	Element columnElement = (Element) columnsElement.getElementsByTagName("column").item(i);
        	Element columnWidth = (Element) columnElement.getElementsByTagName("width").item(0);
        	column.setColumnWidth(Integer.parseInt(columnWidth.getTextContent()));
        	
        	Element title = (Element) columnElement.getElementsByTagName("title").item(0);
        	column.setTitle(title.getTextContent());
        	
        	columnsList.add(column);
        	report.setColumns(columnsList);
        	//System.out.println(column.getTitle() + "      " + column.getColumnWidth());
        }
        
        return report;
        
	}
}
