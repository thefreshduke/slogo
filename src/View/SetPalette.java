package View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import GUIFunctions.GridFunction;

public class SetPalette extends GridFunction{
    ColorSelection myColors;
    public SetPalette(ColorSelection colors){
        myColors=colors;
    }


    @Override
    public void doAction() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doAction(List<? extends Number> newVal) {
        int myIndex=0;
        String myColor="";
        Iterator<Number> it=(Iterator<Number>) newVal.iterator();
        if (newVal.size()!=0){
            myIndex=it.next().intValue();
        }
        while (it.hasNext()){
            myColor+=it.next()+"";
        }
        myColors.setColor(myIndex, myColor);


    }
}