package businesslogic;

import org.junit.Test;

import junit.framework.TestCase;

public class ReceiveEmailWithAttachmentTest extends TestCase {
	 @Test
	    public void testReceiveMail() throws Exception {
	        new ReceiveEmailWithAttachment().receiveEmail("pop",
	                "mailStoreType", "userName", "password");
	    }
	 
}

