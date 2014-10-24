package GUIFunctions;

import View.SingleGrid;

public abstract class GridFunction implements GUIFunction{
	protected SingleGrid myGrid;
	public GridFunction(){
		
	}
	public void setGrid(SingleGrid grid){
		myGrid=grid;
	}
}
