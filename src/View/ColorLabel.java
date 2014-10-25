package View;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import GUIFunctions.ColorFunction;

public class ColorLabel extends Button {
    private boolean isActive;
    private ColorFunction myFunction;

    public ColorLabel (double x, double y, double width, double height, String myName,
            ColorFunction myGUIFunction) {
        this.setText(myName);
        this.setPrefSize(width, height);
        relocate(x, y);
        isActive = false;
        this.addEvent();
        myFunction = myGUIFunction;
    }

    private void addEvent () {
        this.setOnMouseClicked(event -> setActive(!isActive));
    }

    private void setActive (boolean active) {
        isActive = active;
        if (isActive) {
            DropShadow myShadow = new DropShadow();
            myShadow.setColor(Color.DARKBLUE);
            myShadow.setRadius(20);
            setEffect(myShadow);
        } else
            setEffect(null);
    }

    public void isActive (String color) {
        if (isActive) {
            myFunction.doAction(color);
        }
    }
}
