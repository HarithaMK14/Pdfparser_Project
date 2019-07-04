package com.altimetrik.training.project;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReadPdf {
	Connection connect=null; 
    public void ReadAndStore(Connection connect,File file) throws InvalidPasswordException, IOException {
    	int j=1;int k=1;
    	String soldto = null,Invoiceno=null;
    	String shipto=null, Invoicedate=null,remitto=null;
    	String totalinvoice=null;
    	String customerpo=null;
    	
    	try (PDDocument document = PDDocument.load(file)) {

            document.getClass();

            if (!document.isEncrypted()) {
			
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                // split by whitespace
                String lines[] = pdfFileInText.split("\\r?\\n");
                for (int i=0;i<lines.length;i++) {
               	 if(lines[i].trim().equalsIgnoreCase("Invoice No"))
                	{ 
                	System.out.println();	
                	System.out.println("*****INVOICE "+j+"*****");	
                	System.out.println("Invoice number is found");
                	System.out.println("it is " +lines[i+1]);
                	Invoiceno=lines[i+1];
                   	}
               	 if(lines[i].trim().equalsIgnoreCase("Invoice Date"))
                	{ 
                	System.out.println("Invoice Date is found");
                	System.out.println("it is " +lines[i+1]);
                  	Invoicedate=lines[i+1];
                	}
               	 if(lines[i].trim().equalsIgnoreCase("Sold To"))
                	{ 
               		soldto="";
                	System.out.println("Sold To is found");
                	for(int ad=1;ad<=3;ad++)
                		soldto=soldto.concat(lines[i+ad]);
                	System.out.println("it is " +soldto);
                  	}
               	 if(lines[i].trim().equalsIgnoreCase("Ship To"))
                	{ 
               		shipto="";
                	System.out.println("Ship To is found");
                	for(int ad=1;ad<=3;ad++)
                		shipto=shipto.concat(lines[i+ad]);
                	System.out.println("it is " +shipto);
                  	}
               	 if(lines[i].trim().equalsIgnoreCase("Remit To"))
               	 	{ 
               		remitto="";
               		 System.out.println("RemitTo is found");
               		for(int ad=1;ad<=3;ad++)
                		remitto=remitto.concat(lines[i+ad]);
                	System.out.println("it is " +remitto);
                 	}
               	 if(lines[i].trim().equalsIgnoreCase("Customer P.O."))
                	{ 
                	System.out.println("Customer po is found");
                	System.out.println("it is " +lines[i+1]);
                	customerpo=lines[i+1];
                	}
                 if(lines[i].trim().equalsIgnoreCase("Total Invoice")||lines[i].trim().equalsIgnoreCase("Credit"))
                	{ 
                	System.out.println("Total invoice is found");
                  	if(k==3)
                		{totalinvoice=lines[i+4];}
                	else
                		{totalinvoice=lines[i+3];}
                	if(lines[i].trim().equalsIgnoreCase("Credit"))
                		{totalinvoice=lines[i+2];}
                	System.out.println("it is " +totalinvoice);
                	System.out.println();
                	 try{
                		System.out.println();
              			System.out.println();
              	      	String query = " insert into INVOICE_DETAILS (Invoiceno, Invoicedate, Soldto, Shipto, Remitto, Customerpo, Totinvoice, Status)"
               		        + " values (?, ?, ?, ?, ?, ?, ?, ?)";
              	      	PreparedStatement preparedStmt = connect.prepareStatement(query);
              	      	preparedStmt.setString (1, Invoiceno);
              	      	preparedStmt.setString (2, Invoicedate);
              	      	preparedStmt.setString (3, soldto);
              	      	preparedStmt.setString (4, shipto);
              	      	preparedStmt.setString (5, remitto);
              	      	preparedStmt.setString (6, customerpo);
              	      	preparedStmt.setString (7, totalinvoice);
              	      	preparedStmt.setString (8, "NOTAPPROVED");

          		      // execute the preparedstatement
              	      	preparedStmt.execute();
              	      	System.out.println("INSERTED INVOICE "+j+" INTO TABLE");
                	 }
                    catch(SQLException e){
                      System.out.println("EXCEPTION DURING READING AND STORING");
                   	  e.printStackTrace();
                     }
                	 System.out.println();
          			j++;k++;
                	}
                }
            }
    	}
    }
}
             
    		
    	
    	

       

