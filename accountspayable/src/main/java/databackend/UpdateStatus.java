package databackend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateStatus{

	private Scanner sc;
	public void showDetails(Connection connect){
		try {
			Statement statement=connect.createStatement();
			ResultSet rs1=statement.executeQuery("select * from INVOICE_DETAILS");
			System.out.println();
			System.out.println("********INVOICE DETAILS IN DB********");
			while(rs1.next())
				{
				System.out.println(rs1.getString(1) + "\t" + rs1.getString(2) + "\t" +rs1.getString(3) + "\t" +rs1.getString(4) + "\t" + rs1.getString(5) + "\t" +rs1.getString(6) + "\t" + rs1.getString(7) + "\t" +rs1.getString(8));
				}
		}
		catch(Exception e)
		{
			System.out.println("Exception in displaying details..");
			e.printStackTrace();
		}
		finally{
			
		}
					
	}
	public void update(Connection connect) {
		// TODO Auto-generated method stub
		try {
		System.out.println();
		sc = new Scanner(System.in);
		String invoiceno = null;
		String response;
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
			response=sc.next();
		}while(response.equalsIgnoreCase("yes"));
		} 
		catch (Exception e) {
			System.out.println("Exception in updating status");
			e.printStackTrace();
			
		}
		finally{
			sc.close();
		}
   		
	}

}

