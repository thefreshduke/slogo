package GUIFunctions;

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
		for (Node myStamp: active.getChildren()) {
			if (myStamp instanceof StampImage) {
				active.getChildren().remove(myStamp);
			}
		}
	}

	@Override
	public void doAction(List<? extends Number> newVal) {
		// TODO Auto-generated method stub
		
	}

}
