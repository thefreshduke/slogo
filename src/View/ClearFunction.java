package View;

public class ClearFunction implements GUIFunction{
	private Grid myGrid;
	public ClearFunction(Grid myG){
		myGrid=myG;
	}
	@Override
	public void doAction() {
		myGrid.getChildren().clear();
	}

}
