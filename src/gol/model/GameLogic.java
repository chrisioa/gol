package gol.model;

import java.util.ArrayList;

import gol.control.GameController;

public class GameLogic {

	private GameModel model;
	private ArrayList<GameCell> gameCells = new ArrayList<>();
	private Double cellsize;
	private GameController control;
	private boolean gameRunning = true;

	public GameLogic(GameModel gameModel, GameController gameController) {
		this.model = gameModel;
		this.control = gameController;
		gameCells = model.getGameCells();
		cellsize = model.getCellSize();
	}
	// Okay, this should perform a step, maybe change to creating lists with all the cells to be changed
	public void startGame() {
		ArrayList<GameCell> neighbors;
		for (GameCell thisCell : gameCells) {
			neighbors = new ArrayList<>();
			neighbors = checkSurroundings(thisCell.getX(), thisCell.getY());
			if (neighbors.size() > 2 && thisCell.isAlive()) {
				model.deactivateGameCell(thisCell.getX(), thisCell.getY());
			} else if (neighbors.size() >= 2 && !thisCell.isAlive()) {
				model.activateGameCell(thisCell.getX(), thisCell.getY());
			}
		}
	}

	// Return a List of Neighbours
	private ArrayList<GameCell> checkSurroundings(double layoutX, double layoutY) {

		ArrayList<GameCell> neighbors = new ArrayList<>();
		ArrayList<GameCell> gameCells = model.getGameCells();

		for (GameCell cell : gameCells) {
			// Left, Right Hand Neighbors
			if (layoutX - 1 == cell.getX() && layoutY == cell.getY()
					|| layoutX + 1 == cell.getX() && layoutY == cell.getY() ||
					// Upper, Lower Neighbors
					layoutX == cell.getX() && layoutY - 1 == cell.getY()
					|| layoutX == cell.getX() && layoutY + 1 == cell.getY() ||
					// Diagonal Neighbors
					layoutX - 1 == cell.getX() && layoutY - 1 == cell.getY()
					|| layoutX - 1 == cell.getX() && layoutY + 1 == cell.getY()
					|| layoutX + 1 == cell.getX() && layoutY - 1 == cell.getY()
					|| layoutX + 1 == cell.getX() && layoutY + 1 == cell.getY()) {

				if (cell.isAlive()) {
					System.out.println("Cell width: " + cell.getX() + "Cell height: " + cell.getY());
					neighbors.add(cell);
				}
			}
		}
		System.out.println("Neighbors: " + neighbors);
		return neighbors;
	}

	public void pauseGame() {
		gameRunning = false;
	}
}
