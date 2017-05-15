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
		
		MenuItem presetOne = new MenuItem("Kok's Galaxy");
		presetOne.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				control.loadPreset(1);
			}
		});
		// MenuItems zu Menu hinzufuegen
		file.getItems().add(startGame);
		file.getItems().add(pauseGame);
		file.getItems().add(resetGame);
		file.getItems().add(presetOne);
		Menu about = new Menu("About");
		// Menu zu MenuBar hinzufuegen
		this.getMenus().add(file);
		this.getMenus().add(about);
	}
}
