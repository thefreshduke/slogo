
import static org.junit.Assert.*;

import org.junit.Test;

public class TestTurtle {
	/**
	 * Instantiates turtle object whose position is at coordinates (0,0).
	 */
	private Turtle myTurtle = new Turtle(new Position(0, 0));


	/**
	 * Testing Forward 50 call. 
	 */
	@Test
	public void testMoveForward50() {
		/**
		 * Moving turtle forward 50 units, assuming it is already facing right.
		 */
		myTurtle.moveHorizontal(50);
		assertEquals(myTurtle.getXPos(), 50.0, 0.0);
	};
}
