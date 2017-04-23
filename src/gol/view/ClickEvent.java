package gol.view;

import java.util.List;

import gol.control.GameController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ClickEvent implements javafx.event.EventHandler<MouseEvent> {

	private int cellSize;
	private Group board;
	private double tileSize;
	private double scale = 1;
	private GameController controller;

	public ClickEvent(GameStage gameStage, Group board, GameController controller) {
		this.cellSize = gameStage.getCellSize();
		tileSize = gameStage.getTileSize();
		this.board = board;
		this.controller = controller;
	}

	@Override
	public void handle(MouseEvent event) {
		// Create a Label on the clicked Cell
		Label label = createLabel(((int) event.getX() / cellSize), (int) event.getY() / cellSize);

		// Get all Children of the board
		List<Node> labelList = board.getChildren();
		Boolean duplicate = false;

		// Check all existing labels
		for (Node existingLabel : labelList) {
			if (existingLabel instanceof Label) {
				// Check if current found
				if (isLabelEqual(label, (Label) existingLabel)) {
					// Check if alive
					if (existingLabel.isVisible()) {
						// Set visibility false
						//changeVisibilityTo(existingLabel, false);
						controller.deleteCell((int) event.getY() / cellSize, (int) event.getX() / cellSize);
						break;
					} else {
						//If dead, activate
						//changeVisibilityTo(existingLabel, true);
						//Activate in Model
						controller.activateGameCell((int) event.getY() / cellSize, (int) event.getX() / cellSize);
					}
				}
			}
		}

		if (duplicate) {
			System.out.println("Delete Cell");
			controller.deleteCell((int) event.getX() / cellSize, (int) event.getY() / cellSize);

		} else {

		}

		System.out.println("Mouse X-Coordinate: " + event.getX() / cellSize);
		System.out.println("Mouse Y-Coordinate: " + event.getY() / cellSize);

		System.out.println("Label X-Coordinate: " + label.getLayoutX());
		System.out.println("Label Y-Coordinate: " + label.getLayoutY());

	}

	//private void changeVisibilityTo(Node existingLabel, boolean bool) {
	//	existingLabel.setVisible(bool);
	//}

	private Label createLabel(int width, int height) {

		Label label = new Label();
		label.setMinSize(tileSize * scale, tileSize * scale);
		label.setPrefSize(tileSize * scale, tileSize * scale);
		label.setMaxSize(tileSize * scale, tileSize * scale);
		label.setStyle("-fx-background-color: red; -fx-background-radius: 10");
		label.setLayoutX((width + 0.5) * cellSize - tileSize / 2);
		label.setLayoutY((height + 0.5) * cellSize - tileSize / 2);
		label.setVisible(false);
		return label;

	}

	private Boolean isLabelEqual(Label label1, Label label2) {
		return (label1.getLayoutX() == label2.getLayoutX() && label1.getLayoutY() == label2.getLayoutY());
	}

}
