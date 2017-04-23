package gol.model;

import java.util.ArrayList;

public class GameLogic {

	private GameModel model;
	private ArrayList<GameCell> gameCells = new ArrayList<>();
	private Double cellsize;

	public GameLogic(GameModel gameModel) {
		this.model = gameModel;
		gameCells = model.getGameCells();
		cellsize = model.getCellSize();
	}

	public void startGame() {
		//for (GameCell thisCell : gameCells) {
		//	checkSurroundings(thisCell);
		//}
	}

	public void checkSurroundings(GameCell cell) {
		ArrayList<GameCell> neighbors = new ArrayList<>();

		double cellX = cell.getX();
		double cellY = cell.getY();

		for (GameCell currentCell : gameCells) {
			if ((cellX + 1 == currentCell.getX() && cellY + 1 == currentCell.getY())
					|| (cellX - 1 == currentCell.getX() && cellY - 1 == currentCell.getY())
					|| (cellX + 1 == currentCell.getX() && cellY - 1 == currentCell.getY())
					|| (cellX - 1 == currentCell.getX() && cellY + 1 == currentCell.getY())
					|| (cellX == currentCell.getX() && cellY - 1 == currentCell.getY())
					|| (cellX - 1 == currentCell.getX() && cellY == currentCell.getY())
					|| (cellX == currentCell.getX() && cellY + 1 == currentCell.getY())
					|| (cellX + 1 == currentCell.getX() && cellY == currentCell.getY())) {

				System.out.println("NEIGHBOR FOUND: X: " + currentCell.getX() + " Y: " + currentCell.getY());
				// currentCell.setAlive(false);
				neighbors.add(currentCell);
				if (currentCell.isAlive()) {
					if(neighbors.size() > 2){
						currentCell.setAlive(false);
					}
				} else {
					if(neighbors.size() == 3){
						currentCell.setAlive(true);
					}
				}
			}

			// Survivor
			System.out.println("Neighbors: " + neighbors);
			if (neighbors.size() > 2) {
				for (GameCell thisCell : neighbors) {
					thisCell.setAlive(false);
				}

			}
		}
	}
}
