package View;

import java.util.HashSet;

public class GridTracker extends HashSet<Grid> {
	SingleGrid myActiveGrid;
	public GridTracker(){
	}
	public SingleGrid getActiveGrid(){
		return myActiveGrid;
	}
	/**
	 * When the view changes Grid Focus, the function is called to change which Grid is active
	 * @param newActive	represents a Grid that is currently active
	 */
	public void setActiveGrid(SingleGrid newActive){
		if (!this.contains(newActive)){
			this.add(newActive);
		}
		myActiveGrid=(SingleGrid) newActive;
	}
}
