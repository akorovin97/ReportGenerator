package parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.univocity.parsers.tsv.*;


public class TSVParser {
	public List<String[]> getInputData() throws ParserConfigurationException, SAXException, IOException{
		
		TsvParserSettings settings = new TsvParserSettings();
		TsvParser parser = new TsvParser(settings);

		List<String[]> allRows = parser.parseAll(new File("src/resourses/source-data.tsv"),"UTF-16");	
		
		return allRows;	
		}
	
}
