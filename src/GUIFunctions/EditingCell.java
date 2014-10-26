package GUIFunctions;


import turtle.Turtle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
public abstract class EditingCell<T, G> extends TableCell<T, G> {
	protected TextField myTextField;
	public EditingCell(){
		startEdit();
	}
	public void startEdit()
	{
		super.startEdit();
		if (myTextField==null){
			createTextField();
		}
		setText(null);
		setGraphic(myTextField);
		myTextField.selectAll();

	}
	public void cancelEdit(){
		super.cancelEdit();
		setText(getItem().toString());
		setGraphic(null);

	}
	public void updateItem(G item, boolean empty){
		super.updateItem(item, empty);
		if (empty){
			setText(null);
			setGraphic(null);
		}
		else if(isEditing()){
			if (myTextField!=null){
				setText(null);
				setGraphic(myTextField);
			}
		}
		else {
			setText(this.getItem().toString());
			setGraphic(null);
		}

	}

	private void createTextField(){
		myTextField=new TextField();
		myTextField.setMinWidth(this.getWidth()-this.getGraphicTextGap()*2);
		myTextField.setEditable(true);
		myTextField.setText(getText());
		myTextField.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				myTextField.setText(getText());
				setGraphic(myTextField);

			}
		});
		myTextField.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent key) {
				saveEdit(key);
			}

		});
	}
	protected abstract void saveEdit(KeyEvent myKey);
		

}
