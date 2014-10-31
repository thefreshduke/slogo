package GUIFunctions;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import View.GridTracker;
import View.SingleGrid;

public class ClearStamps extends BottomFunctions{
	public ClearStamps(GridTracker grid){
		allGrids=grid;
	}
	@Override
	public void doAction() {
		SingleGrid active=allGrids.getActiveGrid();
		ArrayList<Node> listToRemove=new ArrayList();
		for (Node myStamp: active.getChildren()) {
			if (myStamp instanceof StampImage) {
				listToRemove.add(myStamp);
			}
		}
		allGrids.getActiveGrid().getChildren().removeAll(listToRemove);
	}

	@Override
	public void doAction(List<? extends Number> newVal) {
		// TODO Auto-generated method stub
		
	}

}
