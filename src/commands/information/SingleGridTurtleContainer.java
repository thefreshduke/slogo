package commands.information;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

import turtle.Turtle;

/**
 * An implementation of Turtle Container which represents a container of all
 * tutles in a single grid
 * 
 * @author Duke
 *
 */
public class SingleGridTurtleContainer extends BaseTurtleContainer {

	private Collection<Turtle> myTurtles;

	public SingleGridTurtleContainer() {
		myTurtles = new LinkedHashSet<>();
	}

	public SingleGridTurtleContainer(Turtle turtle) {
		this();
		myTurtles.add(turtle);
	}

	@Override
	public void removeTurtle(int turtleID) {
		for (Turtle turtle : myTurtles) {
			if (turtle.getID() == turtleID) {
				myTurtles.remove(turtle);
			}
		}
	}

	@Override
	public void addTurtle(Turtle turtle, boolean isActive) {
		myTurtles.add(turtle);
		turtle.setActiveStatus(isActive);
	}

	@Override
	public Collection<Turtle> getAllTurtles() {
		return new ArrayList<>(myTurtles);
	}

	@Override
	public Collection<Integer> getActiveTurtlesByID() {
		LinkedHashSet<Integer> activeTurtleIDs = new LinkedHashSet<>();
		for (Turtle turtle : myTurtles) {
			if (turtle != null && turtle.isActive()) {
				activeTurtleIDs.add(turtle.getID());
			}
		}
		return activeTurtleIDs;
	}

	@Override
	public void setTurtleAsActive(int turtleID) {
		for (Turtle turtle : myTurtles) {
			if (turtle.getID() == turtleID) {
				turtle.setActiveStatus(true);
				break;
			}
		}
	}

	@Override
	public void hardSetActiveTurtles(Collection<Integer> turtleIDs) {
		HashSet<Integer> turtleIDSet = new HashSet<>(turtleIDs);
		for (Turtle turtle : myTurtles) {
			if (turtleIDSet.contains(turtle.getID())) {
				turtle.setActiveStatus(true);
			}
			else{
				turtle.setActiveStatus(false);
			}
		}
	}

	@Override
	public Collection<Turtle> getActiveTurtles() {
		ArrayList<Turtle> activeTurtles = new ArrayList<>();
		for (Turtle turtle : myTurtles) {
			if (turtle != null && turtle.isActive()) {
				activeTurtles.add(turtle);
			}
		}
		return activeTurtles;
	}

	@Override
	public Collection<Integer> getAllTurtlesByID() {
		LinkedHashSet<Integer> turtleIDSet = new LinkedHashSet<>();
		for (Turtle turtle : myTurtles) {
			turtleIDSet.add(turtle.getID());
		}
		return turtleIDSet;
	}

	@Override
	public void clear() {
		myTurtles.clear();
	}
}
