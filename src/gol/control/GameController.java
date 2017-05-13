package gol.control;

import java.util.ArrayList;

import gol.model.GameCell;
import gol.model.GameLogic;
import gol.model.GameModel;
import gol.view.GameStage;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.Label;

public class GameController {

	private GameModel model;
	private GameStage view;
	private Integer cellSize;
	private GameLogic logic;

	public GameController(GameModel model) {
		this.model = model;
		view = new GameStage(this);
		cellSize = view.getCellSize();
		model.setCellSize(cellSize);
		setupBindings();
	}

	public void startShow() {
		view.show();
		model.startGame();
	}

	public int getNumberOfCells(int i) {
		if (i == 0) {
			System.out.println("Height = " + model.getGameheight());
			return model.getGameheight();
		} else if (i == 1) {
			System.out.println("Width = " + model.getGamewidth());
			return model.getGamewidth();
		} else {

			System.err.println("getCellNumber 0 or 1!");
			return 0000;
		}

	}

	public boolean checkAlive(double x, double y) {
		return model.isAlive(y, x);
	}

	public void deleteCell(double x, double y) {
	
		model.deactivateGameCell(x, y);
	}

	public void activateGameCell(double layoutX, double layoutY) {
		model.activateGameCell(layoutX, layoutY);

		ArrayList<GameCell> neighbors = checkSurroundings(layoutX, layoutY);
		for(int i = 0; i < neighbors.size(); i++){
			GameCell currentCell = neighbors.get(i);
			System.out.println("X: " + currentCell.getX()+" Y: " + currentCell.getY());
		}

	}

	// Return a List of Neighbours
	private ArrayList<GameCell> checkSurroundings(double layoutX, double layoutY) {

		ArrayList<GameCell> neighbors = new ArrayList<>();
		ArrayList<GameCell> gameCells = model.getGameCells();

		cellSize = view.getCellSize();
		for (GameCell cell : gameCells) {
				//Left, Right Hand Neighbors
			if (layoutX - 1 == cell.getX() && layoutY == cell.getY()||
				layoutX + 1 == cell.getX() && layoutY == cell.getY()||	
				//Upper, Lower Neighbors
				layoutX == cell.getX() && layoutY-1 == cell.getY()||	
				layoutX == cell.getX() && layoutY+1 == cell.getY()||
				//Diagonal Neighbors
				layoutX - 1 == cell.getX() && layoutY - 1 == cell.getY()||
				layoutX - 1 == cell.getX() && layoutY + 1 == cell.getY()||
				layoutX + 1 == cell.getX() && layoutY - 1 == cell.getY()||
				layoutX + 1 == cell.getX() && layoutY + 1 == cell.getY()) {
				
				if (cell.isAlive()) {
					System.out.println("Cell width: " + cell.getX() + "Cell height: " + cell.getY());
					neighbors.add(cell);
				}
			}
		}
		System.out.println("Neighbors: " + neighbors);
		return neighbors;
	}

	

	public void setupBindings() {
		ArrayList<GameCell> gameCells = model.getGameCells();
		ArrayList<Label> labels = view.getLabels();
		
		System.out.println("Labels: " + labels.size() +"\n" + "Cells: " + gameCells.size());
		
		for(int i=0; i<gameCells.size(); i++){
			Label label = labels.get(i);
			GameCell gameCell = gameCells.get(i);
			
			
			BooleanProperty activeProperty = label.visibleProperty();
			BooleanProperty gameCellAlive = gameCell.getIsAlive();
			
			//activeProperty.bind(gameCellAlive);
			gameCellAlive.bindBidirectional(activeProperty);
			System.out.println("Bindings Done!");
		}
	}

	public void resetGame() {
		model.deactivateAllGameCells();
	}

	public void startGame() {
		logic = new GameLogic(model,this);
		logic.startGame();
	}

	public void pauseGame() {
		logic.pauseGame();
	}
}
