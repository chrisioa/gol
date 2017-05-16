package gol.view;

import gol.control.GameController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.text.Text;

public class MyToolbar extends ToolBar {

	private GameController control;
	private int initialSpeed = 100;

	public MyToolbar(String id, GameController control) {
		this.control = control;

		initToolbar();
	}

	private void initToolbar() {
		// Make and set NEXT STEP Button
		Button nextStep = new Button("Next Step");
		nextStep.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				control.performOneStep();
			}
		});
		this.getItems().add(nextStep);

		// Make and set Start Button
		Button startButton = new Button("Start Game");
		startButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				control.startGame();
			}
		});
		this.getItems().add(startButton);

		// Make and set Pause Button
		Button pauseButton = new Button("Pause Game");
		pauseButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				control.pauseGame();
			}
		});
		this.getItems().add(pauseButton);

		//Text Field for GameSpeed
		Text gameSpeedText = new Text("Delay between steps: " + initialSpeed +"ms");
		this.getItems().add(gameSpeedText);
		
		//Make and add Slider for GameSpeed control
		Slider gameSpeedSlider = new Slider(10, 250, initialSpeed);
		control.setGameSpeed(initialSpeed);
		
		gameSpeedSlider.valueProperty().addListener(
		        (observable, oldvalue, newvalue) ->
		        {
		            int i = newvalue.intValue();
		           //This sets the delay in the controller
		           control.setGameSpeed(i);
		           //Text Label that displays delay
		           gameSpeedText.setText("Delay between steps: " + i +"ms");
		        } );
		
		this.getItems().add(gameSpeedSlider);
	
		
		
	}
	
}
