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
	final private int myWidth;
	final private int myHeight;
	final private KeyFrame myFrame;
	final private HashMap<String, Class<? extends Grid>> myGrids=new HashMap<String, Class<? extends Grid>>();
	final Collection<Grid> allGrids;
	private HashMap<String, GUIFunction> myMap;
	public GridFactory(int width, int height, KeyFrame frame, HashMap<String, GUIFunction> myHashMap) throws ClassNotFoundException{
		myWidth=width;
		myHeight=height;
		myFrame=frame;
		myMap=myHashMap;
		allGrids=new ArrayList<Grid>();
		makeMap();
		
	}
	public Grid makeGrid(String s) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Grid myGrid=null;
		myGrid=(Grid) myGrids.get(s).getConstructor(int.class, int.class, KeyFrame.class, HashMap.class, int.class).newInstance(myWidth, myHeight, myFrame, myMap, my_ID++);
		allGrids.add(myGrid);
		return myGrid;

	}
	public void makeMap() throws ClassNotFoundException{
		SingleGrid myGrid=new SingleGrid();
		Class gridClass =myGrid.getClass();
        if(Grid.class.isAssignableFrom(gridClass)){
            myGrids.put("SingleGrid", gridClass);
        }
	}
	public void setGridMap(HashMap myGridMap){
		myMap=myGridMap;
		for (Grid g: allGrids){
			g.setMap(myGridMap);
		}
		
	}
	
}
