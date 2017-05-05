package com.bookstore.services;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.Book;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


public class CsvReport implements Report {
	List<Book> booksList;
	
	public CsvReport(){
		this.booksList= new ArrayList<Book>();
	}
	
	public void generateReport(List<Book> booksList){
		this.booksList=booksList;
		build();
	}
	
	private void build(){
		StyleBuilder boldStyle = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
	                                      .setBorder(stl.pen1Point())
	                                      .setBackgroundColor(Color.LIGHT_GRAY);
		
		try {
			 report()//create new report design
			   .setColumnTitleStyle(columnTitleStyle)
			   .highlightDetailEvenRows()
			   .columns(//add columns
					//          title,    field name    data type
					col.column("Author", "author",   type.stringType()),
					col.column("Title",  "title", 	 type.stringType()),
					col.column("Genre",  "genre",	 type.stringType()),
					col.column("Price",  "price",	 type.doubleType()))
			   .title(cmp.text("OUT OF STOCK:").setStyle(boldCenteredStyle))//shows report title
			   .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle))//shows number of page at page footer
			   .setDataSource(createDataSource())//set datasource
			   .toCsv(new FileOutputStream("csvReport.csv"))
			   ;//.show();//create and show report
			 
		} catch(IOException e){
			e.printStackTrace();
		} catch (DRException e) {
 		   e.printStackTrace();
       }
	}
	 private JRDataSource createDataSource() {
	   DRDataSource dataSource = new DRDataSource("author", "title", "genre","price");
	   for(Book b: booksList){
		   dataSource.add(b.getAuthor(),b.getTitle(),b.getGenre(),b.getPrice());
	   }
	   return dataSource;
    }

//	public static void main(String[] args) {
//		List<Book> booksList = new ArrayList<Book>();
//		booksList.add(new Book(1, "Titlu1", "Autor1", "history", 20, 5.0));
//		booksList.add(new Book(2, "Titlu2", "Autor2", "history", 21, 5.3));
//		booksList.add(new Book(3, "Titlu3", "Autor3", "history", 20, 7.1));
//		booksList.add(new Book(4, "Titlu4", "Autor4", "math", 22, 6.0));
//		booksList.add(new Book(5, "Titlu5", "Autor5", "math", 10, 9.11));
//		booksList.add(new Book(6, "Titlu6", "Autor6", "romance", 15, 11.0));
//		booksList.add(new Book(7, "Titlu7", "Autor7", "business", 22, 2.0));
//		booksList.add(new Book(8, "Titlu8", "Autor8", "fantasy", 32, 12.0));
//		booksList.add(new Book(9, "Titlu9", "Autor9", "geography", 41, 8.12));
//		PdfReport report = new PdfReport(booksList);
//		report.generateReport();
//	}
}
