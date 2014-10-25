package GUIFunctions;

import java.io.File;
import java.util.List;

import View.SingleGrid;
import communicator.MainController;

public class SetLanguage extends LanguageMenu {

    @Override
    public File doAction(String s) {
        AllLanguages myNewLanguage=new AllLanguages(s);
        try {
            return myNewLanguage.getLanguageFile();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void doAction() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doAction(List<? extends Number> newVal) {
        // TODO Auto-generated method stub

    }



}