package generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import parser.TSVParser;

public class Main {

    
    
   

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
    	
    	TSVParser tsvParser = new TSVParser();
    	List<String[]> inputData = tsvParser.getInputData();
    	
    	List<String> result = new ArrayList<>();
    	
    	
    	
    	
    	
        DataGenerator generator = new DataGenerator();
        result = generator.generate(inputData);

        try {

            File fileDir = new File("report.txt");

            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF-16"));

            for (String line : result) {
                out.append(line).append("\r\n");
            }

            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
