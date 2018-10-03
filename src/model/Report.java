package model;

import java.util.ArrayList;

public class Report {

	private int pageWidth;
	private int pageHeight;
	private ArrayList<Column> columns;
	
	public int getPageWidth() {
		return pageWidth;
	}
	public void setPageWidth(int pageWidth) {
		this.pageWidth = pageWidth;
	}
	public int getPageHeight() {
		return pageHeight;
	}
	public void setPageHeight(int pageHeight) {
		this.pageHeight = pageHeight;
	}
	public ArrayList<Column> getColumns() {
		return columns;
	}
	public void setColumns(ArrayList<Column> columns) {
		this.columns = columns;
	}
}
