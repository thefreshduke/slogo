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
	private Collection<Turtle> myActiveTurtles;

	public SingleGridTurtleContainer() {
		myTurtles = new LinkedHashSet<>();
		myActiveTurtles = new LinkedHashSet<>();
	}

	public SingleGridTurtleContainer(Turtle turtle) {
		this();
		myTurtles.add(turtle);
		myActiveTurtles.add(turtle);
	}

	@Override
	public void removeTurtle(int turtleID) {
		Turtle turtleToRemove = null;
		for (Turtle turtle : myTurtles) {
			if (turtle.getID() == turtleID) {
				turtleToRemove = turtle;
			}
		}
		if (turtleToRemove != null) {
			myTurtles.remove(turtleToRemove);
			myActiveTurtles.remove(turtleToRemove);
		}
	}

	@Override
	public void addTurtle(Turtle turtle, boolean isActive) {
		myTurtles.add(turtle);
		if (isActive) {
			myActiveTurtles.add(turtle);
			turtle.setActive();
		} else {
			turtle.setInactive();
		}
	}

	@Override
	public Collection<Turtle> getAllTurtles() {
		return new ArrayList<>(myTurtles);
	}

	@Override
	public Collection<Integer> getActiveTurtlesByID() {
		LinkedHashSet<Integer> activeTurtleIDs = new LinkedHashSet<>();
		for (Turtle turtle : myActiveTurtles) {
			if (turtle != null) {
				activeTurtleIDs.add(turtle.getID());
			}
		}
		return activeTurtleIDs;
	}

	@Override
	public void setTurtleAsActive(int turtleID) {
		for (Turtle turtle : myTurtles) {
			if (turtle.getID() == turtleID) {
				myActiveTurtles.add(turtle);
				break;
			}
		}
	}

	@Override
	public void hardSetActiveTurtles(Collection<Integer> turtleIDs) {
		myActiveTurtles.clear();
		for (Turtle toBeInactiveTurtle : myActiveTurtles) {
			toBeInactiveTurtle.setInactive();
		}
		myActiveTurtles.clear();
		HashSet<Integer> turtleIDSet = new HashSet<>(turtleIDs);
		for (Turtle turtle : myTurtles) {
			if (turtleIDSet.contains(turtle.getID())) {
				turtle.setActive();
				myActiveTurtles.add(turtle);
			}
		}
	}

	@Override
	public Collection<Turtle> getActiveTurtles() {
		return new ArrayList<>(myActiveTurtles);
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
		myActiveTurtles.clear();
	}
}
