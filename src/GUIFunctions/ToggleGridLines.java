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
		myGrid=grid.getActiveGrid();
		translate=gridSize;
		myGridLines=new ArrayList<Line>();
	}
	
	@Override
	public void doAction() {
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
		for (int i=0; i<myGrid.myHeight; i+=translate){
			for (int j=0; j<myGrid.myWidth; j+=translate){
				drawGridLine(i, j);
			}
		}
	}
	
	private void drawGridLine(int y, int x){
		Line verticalGridLine=new Line(x, 0, x, myGrid.myHeight);
		verticalGridLine.setStroke(Paint.valueOf("GREY"));
		verticalGridLine.setStyle("-fx-fill: GREY");
		Line horizontalGridLine=new Line(0, y, myGrid.myWidth, y);
		horizontalGridLine.setStroke(Paint.valueOf("GREY"));
		myGrid.getChildren().addAll(verticalGridLine, horizontalGridLine);
		myGridLines.add(verticalGridLine);
		myGridLines.add(horizontalGridLine);
	}

	@Override
	public void doAction(List<Number> newVal) {
		// TODO Auto-generated method stub
		
	}
}

