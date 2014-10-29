package TableInformation;

import GUIFunctions.EditingCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import turtle.Turtle;

public class EditingTurtleDoubleCell extends EditingCell{
	protected void saveEdit(KeyEvent myKey) {
		if (myKey.getCode()==KeyCode.ENTER){
			String myEdit=myTextField.getText();
			Double myDouble=Double.parseDouble(myEdit);
			if (myEdit.equals(null)){
				myTextField.setText(getText());
			}
			else{
				commitEdit(myDouble);
				myTextField.setText(getItem().toString());
			}
		}
	}
}

