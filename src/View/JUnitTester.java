package View;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTester {

	@Test
	public void testHistory() throws ClassNotFoundException{
		String cmd = "FORWARD 50";
		SlogoView view = new SlogoView();
		view.executeUserCommand(cmd);
		String hist = view.myCommands.peek().getText();
		assertEquals(cmd,hist);
	}
	
}
