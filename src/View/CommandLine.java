package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CommandLine extends HBox {


	public CommandLine(EventHandler<ActionEvent> handler){

		Label label1 = new Label("Commands:");
		TextField text = new TextField();
		Button enter = new Button("Enter");
		enter.setOnAction(handler);
		this.getChildren().addAll(label1, text, enter);
		this.setSpacing(10);	
	}





}
