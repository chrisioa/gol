package gol.model;

import java.util.ArrayList;

import gol.control.GameController;

public class GameTask implements Runnable {
	private ArrayList<GameCell> gameCells = new ArrayList<>();
	private boolean running=true;
	private GameController controller;
	//Wait between steps in ms

	public GameTask(GameController controller) {
		this.controller=controller;
	}
	
	@Override
	public void run() {
		//This represents the life cycle with the gameSpeed being used as a delay between steps
		while(running){
			performStep();	
			try {
				Thread.sleep(getGameSpeed());
			} catch (InterruptedException ignored) {
			}
		}
	
	}

	public void performStep() {
		gameCells = controller.getGameCells();

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
			//Activate cells
			for (GameCell thisCell : toActivate) {
				thisCell.setAlive(true);
			}
			//Deactivate cells
			for (GameCell thisCell : toDeactivate) {
				thisCell.setAlive(false);
			}
	}

	public void pause(){
		running = false;
	}
	
	// Return a List of Neighbours for specified cell
	private ArrayList<GameCell> checkSurroundings(double layoutX, double layoutY) {

		ArrayList<GameCell> neighbors = new ArrayList<>();
		ArrayList<GameCell> gameCells = controller.getGameCells();

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
					neighbors.add(cell);
				}
			}
		}
		return neighbors;
	}

	public int getGameSpeed() {
		return controller.getGameSpeed();
	}

}
