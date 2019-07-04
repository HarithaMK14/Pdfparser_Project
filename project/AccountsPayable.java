package com.altimetrik.training.project;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

public class AccountsPayable {
	Connection conn = null;
	File file;
	String invoice,userName,password,from;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountsPayable ap=new AccountsPayable();
		ap.getEmailAttachment();
		ap.dbConnection();
		ap.ParseAndStore();
		ap.updateStatus();
		ap.sendEmailNotification();
	}
	void getEmailAttachment(){
		ReceiveEmailWithAttachment recv=new ReceiveEmailWithAttachment();
		String pop3Host = "pop.gmail.com";//change accordingly
		String mailStoreType = "pop3";	
		userName = "mkharitha14@gmail.com";//change accordingly
		password = "harsha@14";//change accordingly
		file=recv.receiveEmail(pop3Host, mailStoreType, userName, password);
		from=recv.fromdetail();
	}
	void dbConnection(){
		try {
			String url="jdbc:oracle:thin:@localhost:1521:xe";
  			String user="hr";
  			String pwd="hr";
  			Properties connectionProps=new Properties();
  			connectionProps.put("user",user);
  			connectionProps.put("password",pwd);
  			conn=DriverManager.getConnection(url,connectionProps);
  			System.out.println("connected...");
  			System.out.println("Database connected");
  		}
			catch (Exception dbException) {
			System.out.println(dbException);
			System.out.println("Database Connection Failed");
		}
	}
	void ParseAndStore() 
	{
		ReadPdf readpdf=new ReadPdf();
		try {
			readpdf.ReadAndStore(conn,file);
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void updateStatus(){
		UpdateStatus us=new UpdateStatus();
		
		us.update(conn);
	}
	void sendEmailNotification() {
		Sendemail sendMail = new Sendemail();
		sendMail.send(userName, password, from, "Accounts Payable", "Your Invoice is approved.");
		// sendMail.sed("from","password","to", "Sub", "Content")
	}

}
