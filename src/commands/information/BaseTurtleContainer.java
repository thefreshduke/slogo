package commands.information;

import java.util.Collection;

import turtle.Turtle;

public abstract class BaseTurtleContainer implements ITurtleBehavior, IInformationContainer {

	@Override
	public void rotate(double rotateIncrement) {
		// TODO Auto-generated method stub
		for(Turtle turtle : getActiveTurtles()){
			turtle.rotate(rotateIncrement);
		}
	}

	@Override
	public void move(double xIncrement, double yIncrement) {
		for(Turtle turtle : getActiveTurtles()){
			turtle.move(xIncrement, yIncrement);
		}
	}

	public abstract Collection<Turtle> getAllTurtles();

	public Collection<Turtle> getActiveTurtles(){
		//ArrayList<Turtle> activeTurtles = 
		for(Turtle turtle : getAllTurtles()){

		}
		return null;
	}

	public void setActiveTurtles(Collection<Integer> turtleIDs){

	}
}
