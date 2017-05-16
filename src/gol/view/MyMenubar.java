package gol.view;

import gol.control.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MyMenubar extends MenuBar {
	private GameController control;
	
	public MyMenubar (GameController control) {
		this.control = control;
		initMyMenubar();
	}

	private void initMyMenubar() {
		Menu file = new Menu("File");
		// MenuItems erzeugen
		MenuItem startGame = new MenuItem("Start");
		startGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				control.startGame();
			}
		});
		MenuItem pauseGame = new MenuItem("Pause");
		pauseGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				control.pauseGame();
			}
		});

		MenuItem resetGame = new MenuItem("Reset");
		resetGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				control.resetGame();
			}
		});
		
		//Sub-Menu
		Menu subMenu_Presets = new Menu("Presets");
		//First Submenu
		MenuItem subMenu_presetOne = new MenuItem("Kok's Galaxy");
		subMenu_presetOne.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				control.loadPreset(1);
			}
		});
		//Second Submenu
		MenuItem subMenu_presetTwo = new MenuItem("Gosper Glider Gun");
		subMenu_presetTwo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				control.loadPreset(2);
			}
		});
		subMenu_Presets.getItems().addAll(subMenu_presetOne,subMenu_presetTwo);
		
		// MenuItems zu Menu hinzufuegen
		file.getItems().add(startGame);
		file.getItems().add(pauseGame);
		file.getItems().add(resetGame);
		file.getItems().add(subMenu_Presets);
		Menu about = new Menu("About");
		// Menu zu MenuBar hinzufuegen
		this.getMenus().add(file);
		this.getMenus().add(about);
	}
}
