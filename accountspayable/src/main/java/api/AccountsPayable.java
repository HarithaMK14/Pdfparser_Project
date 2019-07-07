package api;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import businesslogic.ReadPdf;
import businesslogic.ReceiveEmailWithAttachment;
import businesslogic.Sendemail;
import databackend.DBConnect;
import databackend.UpdateStatus;

public class AccountsPayable {
	Connection conn = null;
	File file;
	String invoice,userName,password,from;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountsPayable ap=new AccountsPayable();
		System.out.println("Invoice parsing and processing");
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
		userName = "xyz@gmail.com";//change accordingly
		password = "xyz";//change accordingly
		file=recv.receiveEmail(pop3Host, mailStoreType, userName, password);
		from=recv.fromdetail();
	}
	void dbConnection(){
		DBConnect d=new DBConnect();
		conn=d.dbconn();
	}
	void ParseAndStore() 
	{
		ReadPdf readpdf=new ReadPdf();
		try {
			readpdf.ReadAndStore(conn,file);
			System.out.println("Invoice details stored in db");
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			System.out.println("InvalidPasswordException occured...");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOException occured...");
			e.printStackTrace();
		}
	}
	void updateStatus(){
		UpdateStatus us=new UpdateStatus();
		us.showDetails(conn);
		us.update(conn);
	}
	void sendEmailNotification() {
		Sendemail sendMail = new Sendemail();
		sendMail.send(userName, password, from, "Accounts Payable", "Your Invoice is approved.");
	}
}
