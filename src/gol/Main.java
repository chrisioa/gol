package gol;

import gol.control.GameController;
import gol.model.GameModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		// Create Model
		GameModel model = new GameModel(20,35);
		// Create Controller and view
		GameController controller = new GameController(model);
		// Show the view via controller
		controller.startShow();

	}

}
