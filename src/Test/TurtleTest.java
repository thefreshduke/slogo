package Test;
import static org.junit.Assert.*;
import javafx.scene.input.KeyCode;




import org.junit.Test;

import turtle.Position;


public class TurtleTest {
	private TestTurtle myTestTurtle;
	public TurtleTest(){

		myTestTurtle=new TestTurtle(new Position(0,0), null);


	}
	@Test

	public void TestMovingKeys(){
		for (KeyCode key: KeyCode.values()){
			myTestTurtle.move(key);
			if (key==KeyCode.RIGHT||key==KeyCode.LEFT){
				if (key==KeyCode.RIGHT)
					assertEquals("Moving Right", myTestTurtle.myPosition.getXPos(), myTestTurtle.velocity, 0);		
				else if (key==KeyCode.LEFT)
					assertEquals("Moving Left", myTestTurtle.myPosition.getXPos(), -myTestTurtle.velocity,0);
				noYMovement();
			}
			else if(key==KeyCode.UP||key==KeyCode.DOWN){
				if (key==KeyCode.UP)
					assertEquals("Moving Up", myTestTurtle.myPosition.getYPos(),  -myTestTurtle.velocity,0);	
				else if (key==KeyCode.DOWN)
					assertEquals("Moving Down", myTestTurtle.myPosition.getYPos(), myTestTurtle.velocity,0);	
				noXMovement();
			}
			else{
				noXMovement();
				noYMovement();
			}
			myTestTurtle.setPosition(0, 0);
		}
	}
	private void noXMovement(){
		assertEquals("Didn't move", myTestTurtle.myPosition.getXPos(), 0, 0);
	}
	private void noYMovement(){
		assertEquals("Didn't move", myTestTurtle.myPosition.getYPos(), 0, 0);
	}

}
