package GUIFunctions;
import java.util.ArrayList;
import java.util.HashSet;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class VariableTable extends Pane {
	private ArrayList<UserInput> myVariables;
	private TableView myTableView;
	private TableView myUserView;
	public VariableTable(){
		myTableView=new TableView();
		myUserView=new TableView();
		myVariables=new ArrayList();
		Scene scene = new Scene(new Group());
		Stage newStage=new Stage();
		newStage.setTitle("Variable Table");
		newStage.setWidth(500);
		newStage.setHeight(500);
		Label label = new Label("My Variable Table");
		label.setFont(new Font("Arial", 20));
		Timeline time=new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		time.getKeyFrames().add(build());
		time.play();
		myTableView.setEditable(true);
		TableColumn myVariableColumn=new TableColumn("Variable");
		TableColumn myVariableName=new TableColumn("Name");
		TableColumn myValue=new TableColumn("Value");
		myValue.setCellValueFactory(new Callback<CellDataFeatures<UserInput, Double>, ObservableValue<Double>>() {
			@Override
			public ObservableValue<Double> call(
					CellDataFeatures<UserInput, Double> myData) {
					if (myData.getValue() instanceof Variable){
						Variable myVariable=(Variable)myData.getValue();
						return new ReadOnlyObjectWrapper<Double> (myVariable.getValue());
					}
						return null;
			}
		});
		
		myVariableName.setCellValueFactory(new Callback<CellDataFeatures<UserInput, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(
					CellDataFeatures<UserInput, String> myData) {
				return new ReadOnlyObjectWrapper<String> (myData.getValue().getName());
			}
		});
		myVariableColumn.getColumns().addAll(myVariableName, myValue);
		
		TableColumn myUserFunctions=new TableColumn("User Functions");
		myUserFunctions.setPrefWidth(200);
		myUserFunctions.setCellValueFactory(new Callback<CellDataFeatures<Variable, Double>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(
					CellDataFeatures<Variable, Double> myData) {
				return new ReadOnlyObjectWrapper<String> (myData.getValue().getName());
			}
		});
		this.getChildren().addAll(myTableView);
		((Group) scene.getRoot()).getChildren().addAll(this);
		newStage.setScene(scene);
		newStage.show();
		myTableView.getColumns().addAll(myVariableColumn, myUserFunctions);
		newStage.show();
	}

	private KeyFrame build() {
		Duration myDuration=Duration.millis(10);
		KeyFrame myFrame=new KeyFrame(myDuration, new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				update();
			}
		});
		return myFrame;
	}

	public void addVariables(ArrayList<UserInput> myVars){
		myVariables=myVars;
	}

	public void update(){
		myTableView.getItems().clear();
		ObservableList<UserInput> myObservableList=FXCollections.observableArrayList();
		myTableView.getItems().clear();
		for (UserInput myVar: myVariables){
			myObservableList=FXCollections.observableArrayList();
			myObservableList.add(myVar);
		}
		myTableView.setItems((myObservableList));
	}

}

