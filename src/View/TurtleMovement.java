package View;

import java.awt.event.KeyEvent;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import turtle.Turtle;

public abstract class TurtleMovement {
	Turtle myTurtle;
	public TurtleMovement(Turtle t){
		myTurtle=t;
		EventHandler<KeyEvent> move=new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				t.move(e.getKeyCode());
			}
		}
	}
}
