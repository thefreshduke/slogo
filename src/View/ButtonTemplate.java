package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import GUIFunctions.GUIFunction;

public class ButtonTemplate extends Button implements UserObjects {
    int textSize;

    /**
     * Constructor
     * 
     * @param s
     *            Label for the Button
     * @param handler
     *            Event for the Button to react upon
     */
    public ButtonTemplate (String s, double x, double y, GUIFunction myFunction) {
        this.relocate(x, y);
        this.setText(s);
        this.addEvent(event -> myFunction.doAction());
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> actionOnMouseHover());
        this.addEventHandler(MouseEvent.MOUSE_EXITED, event -> actionOnMouseExit());
        setStyle(textSize);
        textSize = 15;
        this.setPrefSize(75, 55);
    }

    public ButtonTemplate (String s, double x, double y, GUIFunction myFunction, int width,
            int height) {
        this.relocate(x, y);
        this.setText(s);
        this.addEvent(event -> myFunction.doAction());
        this.setPrefSize(width, height);
        setStyle(textSize);
    }

    @Override
    public void addEvent (EventHandler<ActionEvent> handler) {
        this.setOnAction(handler);
    }

    public void setStyle (int text) {
        setTextFill(Paint.valueOf("WHITE"));
        setStyle("-fx-background-color: BLACK; -fx-border-color: WHITE; -fx-font-size:" + text);
    }

    public void actionOnMouseHover () {
        setStyle(textSize + 2);
    }

    public void actionOnMouseExit () {
        setStyle(textSize - 2);
    }

}
