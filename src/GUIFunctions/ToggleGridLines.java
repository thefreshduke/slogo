package GUIFunctions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import View.GridTracker;
import View.SingleGrid;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class ToggleGridLines extends PersonalizeMenu{
	boolean showGridLines;
	int translate;
	private Collection<Line> myGridLines;
	private SingleGrid myGrid;
	
	public ToggleGridLines(GridTracker grid, int gridSize){
		showGridLines=true;
		translate=gridSize;
		myGridLines=new ArrayList<Line>();
		allGrids=grid;
	}
	
	@Override
	public void doAction() {
		myGrid=allGrids.getActiveGrid();
		if (showGridLines){
			makeGridLines();
			showGridLines=false;
		}
		else{
			removeGridLines();
			showGridLines=true;
		}
	}

	public void removeGridLines(){
		myGrid.getChildren().removeAll(myGridLines);
		myGridLines.clear();
	}
	
	public void makeGridLines(){
		for (int i=0; i<myGrid.getMyHeight(); i+=translate){
			for (int j=0; j<myGrid.getMyWidth(); j+=translate){
				drawGridLine(i, j);
			}
		}
	}
	
	private void drawGridLine(int y, int x){
		Line verticalGridLine=new Line(x, 0, x, myGrid.getMyHeight());
		verticalGridLine.setStroke(Paint.valueOf("GREY"));
		verticalGridLine.setStyle("-fx-fill: GREY");
		Line horizontalGridLine=new Line(0, y, myGrid.getMyWidth(), y);
		horizontalGridLine.setStroke(Paint.valueOf("GREY"));
		myGrid.getChildren().addAll(verticalGridLine, horizontalGridLine);
		myGridLines.add(verticalGridLine);
		myGridLines.add(horizontalGridLine);
	}

	@Override
	public void doAction(List<? extends Number> newVal) {
		// TODO Auto-generated method stub
		
	}
}

