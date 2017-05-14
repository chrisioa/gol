package gol.model;

import java.util.ArrayList;

public class GameTask implements Runnable {
	private ArrayList<GameCell> gameCells = new ArrayList<>();
	private GameModel model;

	public GameTask(GameModel model) {
		this.model = model;
	}

	@Override
	public void run() {
		gameCells = model.getGameCells();


			ArrayList<GameCell> neighbors;
			ArrayList<GameCell> toActivate = new ArrayList<>();
			ArrayList<GameCell> toDeactivate = new ArrayList<>();
			// Check surroundings for every cell and either add it to toActivate
			// or toDeactivate
			for (GameCell thisCell : gameCells) {
				neighbors = new ArrayList<>();
				neighbors = checkSurroundings(thisCell.getX(), thisCell.getY());
				if (thisCell.isAlive() && neighbors.size() < 2 || thisCell.isAlive() && neighbors.size() > 3) {
					toDeactivate.add(thisCell);
				} else if (neighbors.size() == 3 && !thisCell.isAlive()) {
					toActivate.add(thisCell);
				}
			}

			for (GameCell thisCell : toActivate) {
				System.out.println("Set alive");
				thisCell.setAlive(true);
			}
			for (GameCell thisCell : toDeactivate) {
				thisCell.setAlive(false);
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	}

	// Return a List of Neighbours for specified cell
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

}
