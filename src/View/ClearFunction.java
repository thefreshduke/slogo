package View;

public class ClearFunction implements GUIFunction{
	private SingleGrid myGrid;
	public ClearFunction(SingleGrid myG){
		myGrid=myG;
	}
	@Override
	public void doAction() {
		myGrid.getChildren().clear();
	}

}
