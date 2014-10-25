package GUIFunctions;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EditingTurtleBooleanCell extends EditingCell {

    @Override
    protected void saveEdit (KeyEvent myKey) {
        if (myKey.getCode() == KeyCode.ENTER) {
            String myEdit = myTextField.getText();
            Boolean myBooleanEdit = Boolean.parseBoolean(myEdit);
            if (myEdit.equals(null)) {
                myTextField.setText(getText());
            } else {
                commitEdit(myBooleanEdit);
                myTextField.setText(getItem().toString());
            }
        }

    }

}
