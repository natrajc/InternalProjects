package com.nttdata.timetracker.controller;

import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.PDPage;
import org.pdfbox.util.Matrix;
import org.pdfbox.util.PDFTextStripper;
import org.pdfbox.util.PDFTextStripperByArea;











import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;

public class MainTest {

	public static void main(String[] args) throws IOException, FileNotFoundException {
	/*    File file = new File("fileName.pdf");
        PDDocument document = PDDocument.load(file);
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition( true );
        Rectangle rect1 = new Rectangle( 50, 140, 60, 20 );
        Rectangle rect2 = new Rectangle( 110, 140, 20, 20 );
        stripper.addRegion( "row1column1", rect1 );
        stripper.addRegion( "row1column2", rect2 );
        List allPages = document.getDocumentCatalog().getAllPages();
        PDPage firstPage = (PDPage)allPages.get( 2 );
        stripper.extractRegions( firstPage );
        System.out.println(stripper.getTextForRegion( "row1column1" ));
        System.out.println(stripper.getTextForRegion( "row1column2" ));
*/
		
		//=========================================
		/*System.out.println("Getting contents from PDF: " );//+ f.getName());
		File f = new File("D:\\april.pdf");
		FileInputStream fis = null;
		
			fis = new FileInputStream(f);
		
		PDFParser parser = new PDFParser(fis); 
		parser.parse(); 
		PDDocument pdfDocument = parser.getPDDocument(); 
		PDFTextStripper stripper = new PDFTextStripper(); 
		
		Matrix value =  new Matrix();
		AffineTransform af = new AffineTransform();
		af.setToScale(1.00,1.00);
		value.setFromAffineTransform(af );
		stripper.setTextLineMatrix(value);

		String contents = stripper.getText(pdfDocument); 
		System.out.println(contents);
		StringReader reader = new StringReader(contents); 
		*/
		//======================================================
		
		
		String regex = "[0-9]+";
		String data = "1313";
		System.out.println(data.matches(regex));
		String regex1 = "[0-9]+";
		String data1 = "     112121     sdf   ";
		System.out.println(data1.matches(regex1));
		String []arr = data1.split("\\s");  
		System.out.println(arr.length);
		System.out.println(data1.replaceAll("\\s+", " ").trim());
		
		
		/*
		File f=new File("D:\\april.pdf");
	    FileInputStream fileIn;
	    PdfReader reader;
	    try {
	      fileIn=new FileInputStream(f);
	      reader=new PdfReader(fileIn);
	      HashMap<String, String> merged=reader.getInfo();
	    //  ByteArrayInputStream bIn=new ByteArrayInputStream(merged);
	      //BufferedReader bR=new BufferedReader(new InputStreamReader(bIn));
	      //String line;
	      //while ((line=bR.readLine()) != null) {
	        //System.out.println(line);
	      //}
	      reader.close();
	      fileIn.close();
	    }
	 catch (    IOException e) {
	      System.err.println("Couldn't read file '" );
	      System.err.println(e);
	    }*/
		
		
		
		
	/*	float width = 612;
		float height = 792;
		float hX = 320, tX = 340, cX = 100;
		float hY = 0, tY = 580, cY = 200;
		float hW = width - hX, tW = width - tX, cW = 100;
		float hH = 80, tH = height - tY, cH = 60;
		Rectangle header = new Rectangle( 150, 150);
		//		Rectangle totals = new Rectangle(cH, cH, cH, cH);
		//totals.setBounds(tX, tY, tW, tH);
		//Rectangle customer = new Rectangle();
		//customer.setBounds(cX, cY, cW, cH);
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		//stripper.
		//stripper.addRegion("totals", totals);
		//stripper.addRegion("customer", customer);
		stripper.setSortByPosition(true);
		int j = 0;
		List pages = pd.getDocumentCatalog().getAllPages();
		for (PDPage page : pages) {
			stripper.extractRegions(page);
			List regions = stripper.getRegions();
			for (String region : regions) {
				String text = stripper.getTextForRegion(region);
				System.out.println("Region: " + region + " on Page " + j);
				System.out.println("\tText: \n" + text);
			}
			j++;
		}*/
	}
	
	
	

}
