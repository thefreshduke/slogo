package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CommandLine extends HBox {


	public CommandLine(EventHandler<ActionEvent> handler){

		Label label = new Label("Commands:");
		TextField text = new TextField();
		Button enter = new Button("Enter");
		enter.setOnAction(handler);
		this.getChildren().addAll(label, text, enter);
		this.setSpacing(10);	
	}



}
