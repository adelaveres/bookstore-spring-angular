package com.bookstore.services;

public class ReportFactory {
	
	public Report getReport(String reportType){
		if(reportType == null)
			return null;
		if(reportType.equalsIgnoreCase("csv"))
			return new CsvReport();
		if(reportType.equalsIgnoreCase("pdf"))
			return new PdfReport();
		return null;		
	}
}
