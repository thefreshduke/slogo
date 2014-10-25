package GUIFunctions;

import java.io.File;
import java.util.List;

public class SetLanguage extends LanuageMenu {

    @Override
    public File doAction (String s) {
        AllLanguages myNewLanguage = new AllLanguages(s);
        try {
            return myNewLanguage.getLanguageFile();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void doAction () {
        // TODO Auto-generated method stub

    }

    @Override
    public void doAction (List<Number> newVal) {
        // TODO Auto-generated method stub

    }

}
