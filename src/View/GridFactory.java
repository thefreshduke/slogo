package View;

import java.awt.List;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import commands.BaseCommand;
import javafx.animation.KeyFrame;
import GUIFunctions.GUIFunction;

public class GridFactory {
	static private int my_ID=0;
	final private KeyFrame myFrame;
	final private HashMap<String, Class<? extends Grid>> myGrids=new HashMap<String, Class<? extends Grid>>();
	final Collection<Grid> allGrids;
	private HashMap<String, GUIFunction> myMap;
	
	/**
	 * 
	 *
	 * @param frame						The frame of the View's Timeline
	 * @param myHashMap					the Map corresponding the the GUIFunctions
	 * @throws ClassNotFoundException
	 */
	public GridFactory(KeyFrame frame, HashMap<String, GUIFunction> myHashMap) throws ClassNotFoundException{
		myFrame=frame;
		myMap=myHashMap;
		allGrids=new ArrayList<Grid>();
		makeGridClassMap();
		
	}
	
	/**
	 * Makes a Grid of the desired class
	 * @param typeOfGrid 					the name of the Grid Class to be made
	 * @return								the Grid that was just created
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public Grid makeGrid(String typeOfGrid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Grid myGrid=null;
		myGrid=(Grid) myGrids.get(typeOfGrid).getConstructor(KeyFrame.class, HashMap.class, int.class).newInstance(myFrame, myMap, my_ID++);
		allGrids.add(myGrid);
		return myGrid;
	}
	private void makeGridClassMap() throws ClassNotFoundException{
		SingleGrid myGrid=new SingleGrid();
		Class gridClass =myGrid.getClass();
        if(Grid.class.isAssignableFrom(gridClass)){
            myGrids.put("SingleGrid", gridClass);
        }
	}
	
	/**
	 * Sets the GUIFunction Map for all the Grids made in the Factory
	 * @param myGridMap	represents the new GUIFunction Map for the Grid to use
	 */
	public void setGridMap(HashMap myGridMap){
		myMap=myGridMap;
		for (Grid g: allGrids){
			g.setMap(myGridMap);
		}	
	}
}
