package View;

import java.util.List;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;

public class SlogoMenu extends MenuBar {

	
	public SlogoMenu(){
		
		createMenus();
	}
	
	public void createMenus(){
		Menu fileMenu = new Menu("File");
		Menu languages = new Menu("Languages");
		Menu help = new Menu("Help");

		this.createMenuItemsUnderFile(fileMenu);
		this.createMenuItemsUnderLanguages(languages);
		this.createMenuItemsUnderHelp(help);
		
		this.getMenus().addAll(fileMenu, languages, help);
	}
	
	public void createMenuItemsUnderFile(Menu fileMenu){
		
		MenuItem exportItem = new MenuItem("Export to XML");
		MenuItem importItem = new MenuItem("Import to XML");
	
		fileMenu.getItems().addAll(exportItem, importItem);
	}
	
	public void createMenuItemsUnderLanguages(Menu languages){
		
		MenuItem english = new MenuItem("English");
		MenuItem french = new MenuItem("French");
		MenuItem portugese = new MenuItem("Portugese");
		MenuItem italian = new MenuItem("Italian");
		MenuItem russian = new MenuItem("Russian");
		MenuItem chinese = new MenuItem("Chinese");
		
		languages.getItems().addAll(english, french, portugese,
				 italian, russian, chinese);

	}
	
	public void createMenuItemsUnderHelp(Menu help){
		MenuItem helpPage = new MenuItem("Help Page");
		
		help.getItems().add(helpPage);
		
	}
	
}
