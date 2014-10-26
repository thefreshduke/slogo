package turtle;

import java.util.HashMap;

import javafx.scene.input.KeyCode;

public class Direction {
	private MoveDirection direction;
	public HashMap<KeyCode, MoveDirection> myDirections=new HashMap<KeyCode, MoveDirection>();
	public Direction(KeyCode e){
		makeDirectionMap();
		direction=myDirections.get(e);
		if (direction==null){
			direction=new NullDirection();
		}
	}
	public Position move(Position p, int speed){
		return direction.move(p, speed);
	}
	public void makeDirectionMap(){
		myDirections.put(KeyCode.RIGHT, new Right());
		myDirections.put(KeyCode.LEFT, new Left());
		myDirections.put(KeyCode.UP, new Up());
		myDirections.put(KeyCode.DOWN, new Down());
		
	}
}
