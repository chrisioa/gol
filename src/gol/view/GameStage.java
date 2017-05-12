package gol.view;

import java.util.ArrayList;
import java.util.stream.IntStream;

import gol.control.GameController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameStage extends Stage {

	private ArrayList<Label> allLabels = new ArrayList<Label>();
	private final int cellsNumberWidth;
	private final int cellsNumberHeight;
	private double scale = 1;
	private static final Integer cellSize = 30;
	private static final double tileSize = cellSize - 5;
	private Group board;
	private GameController control;

	public GameStage(GameController control) {
		this.control = control;
		cellsNumberHeight = control.getNumberOfCells(0);
		cellsNumberWidth = control.getNumberOfCells(1);

		Group gridGroup = new Group();
		board = new Group(gridGroup);

		this.setTitle("New Game");
		this.setScene(new Scene(board, 512, 512));

		this.setMinWidth(700);
		this.setMinHeight(550);
		this.setMaxHeight(1024);

		// Create and draw the grid on the board
		drawGrid(gridGroup);
		// Create all the labes (visibility off)
		createLabels(board);
		// set clickevent
		board.setOnMouseClicked(new ClickEvent(this, board, control));
		
	}

	

	private void createLabels(Group board) {
		
		for(int i = 0; i<cellsNumberHeight;i++){
			for(int j = 0; j<cellsNumberWidth; j++){
				Label label = createLabel(i,j);
				board.getChildren().add(label);
				allLabels.add(label);
			}
		}
	
	}

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

	private void drawGrid(Group group) {
		// create background square grid

		IntStream.range(0, cellsNumberHeight).boxed()
				.forEach(i -> IntStream.range(0, cellsNumberWidth).boxed().forEach(j -> {
					Rectangle cell = new Rectangle(i * cellSize, j * cellSize, cellSize, cellSize);
					cell.setFill(Color.WHITE);
					cell.setStroke(Color.GREY);
					group.getChildren().add(cell);
				}));

	}

	public int getCellSize() {
		return cellSize;
	}

	public double getTileSize() {
		return tileSize;
	}
	
	public ArrayList<Label> getLabels(){
		return allLabels;
	}

//	public void deactivateLabel(double x, double y) {
//		for(Label label : allLabels){
//			if(x==label.getLayoutX() && y==label.getLayoutY()){
//				label.setVisible(false);
//				break;
//			}
//		}
//	}

}
