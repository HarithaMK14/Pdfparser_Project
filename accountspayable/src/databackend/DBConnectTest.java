package databackend;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBConnectTest {

	@Test
	public void test() {
		DBConnect d = new DBConnect();
		
		assertEquals(true,d instanceof DBConnect);
		
		DBConnect das=new DBConnect();
		
		assertNotEquals(false,das instanceof DBConnect);
	}

}
