package generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Column;
import model.Report;
import parser.XMLParser;

public class DataGenerator {

    public List<String> generate(List<String[]> inputData) throws ParserConfigurationException, SAXException, IOException {
    	
        List<String> result = new ArrayList<>();
        XMLParser xmlParser = new XMLParser();
        Report report = new Report();
        report = xmlParser.getReport();
        RowGenerator rowGenerator = new RowGenerator();
        
        rowGenerator.generateHead(report);

        rowGenerator.generateRowDelimiter(report);
        result.add(rowGenerator.getHeadRow());
        result.add(rowGenerator.getDelimiterRow());
        for (String[] dataRow : inputData) {
           
            int i = 0;
            int height = 1;
            for (Column column : report.getColumns()) {
                if(dataRow[i].length() / column.getColumnWidth() >= 1){
                    if(height < dataRow[i].length() / column.getColumnWidth()) {
                        height = dataRow[i].length() / column.getColumnWidth();
                    }
                    if(dataRow[i].length() % column.getColumnWidth() > 0){
                        height++;
                    }
                }
                i++;
            }

            for (int j = 0; j < height; j++) {
                String[] inner = new String[dataRow.length];
                for (int k = 0; k < dataRow.length; k++) {
                    if(dataRow[k].length() < report.getColumns().get(k).getColumnWidth()) {
                        if(j == 0) {
                            inner[k] = dataRow[k];
                        } else {
                            inner[k] = "";
                        }
                    } else {
                        int rowMultiplier = report.getColumns().get(k).getColumnWidth()*j;
                        if (rowMultiplier >= dataRow[k].length()) {
                            inner[k] = "";
                        } else if (rowMultiplier + report.getColumns().get(k).getColumnWidth()-1 >= dataRow[k].length()){
                            inner[k] = dataRow[k].substring(rowMultiplier);
                        } else {
                            inner[k] = dataRow[k].substring(rowMultiplier, rowMultiplier + report.getColumns().get(k).getColumnWidth());
                        }
                    }
                }
                result.add(rowGenerator.generate(inner, report));
                if (result.size() % report.getPageHeight() == 0) {
                    result.add("~");
                    result.add(rowGenerator.getHeadRow());
                    result.add(rowGenerator.getDelimiterRow());
                } else if(j == height-1) {
                    result.add(rowGenerator.getDelimiterRow());
                }
            }
        }

        return result;
    }
}
