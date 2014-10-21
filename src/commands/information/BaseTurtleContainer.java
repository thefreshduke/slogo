package commands.information;

import java.util.Collection;

import turtle.Turtle;

public abstract class BaseTurtleContainer implements ITurtleBehavior, IInformationContainer{

	@Override
	public void rotate(double rotateIncrement) {
		// TODO Auto-generated method stub
		for(Turtle turtle : getActiveTurtlesByTurtle()){
			turtle.rotate(rotateIncrement);
		}
	}

	@Override
	public void move(double xIncrement, double yIncrement) {
		for(Turtle turtle : getActiveTurtlesByTurtle()){
			turtle.move(xIncrement, yIncrement);
		}
	}

	public abstract Collection<Turtle> getAllTurtles();
	
	public Collection<Integer> getActiveTurtlesById(){
		//ArrayList<Turtle> activeTurtles =s 
		for(Turtle turtle : getAllTurtles()){
			
		}
		return null;
	}
	
	public void setActiveTurtles(Collection<Integer> turtleIDs){
		
	}
	
	private Collection<Turtle> getActiveTurtlesByTurtle(){
		return null;
	}
}
