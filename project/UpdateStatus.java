package com.altimetrik.training.project;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateStatus extends ReadPdf{

	private Scanner sc;

	public void update(Connection connect) {
		// TODO Auto-generated method stub
		try {
		Statement statement=connect.createStatement();
		ResultSet rs1=statement.executeQuery("select * from INVOICE_DETAILS");
		System.out.println();
		System.out.println("****INVOICE NUMBERS IN DB****");
		while(rs1.next())
			{
				System.out.println(rs1.getString(1));
			}	
		System.out.println();
		sc = new Scanner(System.in);
		String invoiceno = null;
		String str2;
		do
		{
			System.out.println("ENTER INVOICE NUMBER TO APPROVE IT:...");
	   		invoiceno=sc.next();
	   		System.out.println("accepted no.");	
			PreparedStatement ps = connect.prepareStatement("UPDATE INVOICE_DETAILS SET Status = ? WHERE Invoiceno = ?");
		    ps.setString(1,"APPROVED");
		    ps.setString(2,invoiceno);
	        ps.executeUpdate();
			ps.close();
			System.out.println("updated");
			System.out.println("DO U WANT TO UPDATE ONCE MORE...ENTER YES");
			str2=sc.next();
		}while(str2.equalsIgnoreCase("yes"));
		} 
		catch (Exception e) {
			System.out.println("Exception in updating status");
			e.printStackTrace();
			
		}
   		
	}

}

