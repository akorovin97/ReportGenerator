package generator;

import org.apache.commons.lang3.StringUtils;

import model.Column;
import model.Report;

public class RowGenerator {
	
	    String row;
	    String headRow;
	    String delimiterRow;
	    

	    public String getRow() {
	        return row;
	    }

	    public void setRow(String row) {
	        this.row = row;
	    }

	    public String getHeadRow() {
	        return headRow;
	    }

	    public void setHeadRow(String headRow) {
	        this.headRow = headRow;
	    }

	    public String getDelimiterRow() {
	        return delimiterRow;
	    }

	    public void setDelimiterRow(String delimiterRow) {
	        this.delimiterRow = delimiterRow;
	    }

	    public String generate(String[] data, Report report){
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append('|');
	        for (int i = 0; i < report.getColumns().size(); i++) {
	            stringBuilder.append(generateColumn(data[i], report.getColumns().get(i).getColumnWidth()));
	        }
	        return stringBuilder.toString();
	    }

	    private StringBuilder generateColumn(String s, int columnWidth) {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append(' ');
	        stringBuilder.append(s);
	        int diffSize = columnWidth - s.length() ;
	        if(diffSize > 0) {
	            stringBuilder.append(StringUtils.repeat(" ", diffSize));
	        }
	        stringBuilder.append(' ').append('|');
	        return stringBuilder;
	    }

	    public void generateRowDelimiter(Report report) {
	        delimiterRow = StringUtils.repeat('-', report.getPageWidth());
	    }

	    public void generateHead(Report report) {

	        String[] columnsTitlesStringArray = new String[report.getColumns().size()];
	        int i = 0;
	        for (Column column: report.getColumns()) {
	            columnsTitlesStringArray[i] = column.getTitle();
	            i++;
	        }
	        headRow = generate(columnsTitlesStringArray, report);

	    }
}
