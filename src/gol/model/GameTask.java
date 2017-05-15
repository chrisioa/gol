package gol.model;

import java.util.ArrayList;

import gol.control.GameController;

public class GameTask implements Runnable {
	private ArrayList<GameCell> gameCells = new ArrayList<>();
	private boolean running=true;
	private GameController controller;

	public GameTask(GameController controller) {
		this.controller=controller;
	}
	
	@Override
	public void run() {
	
		while(running){
			performStep();	
			try {
				//TODO: Make a slider or something 
				//This effectively is the speed of the game (Wait between steps)
				Thread.sleep(500);
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

			for (GameCell thisCell : toActivate) {
				System.out.println("Set alive");
				thisCell.setAlive(true);
			}
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
					System.out.println("Cell width: " + cell.getX() + "Cell height: " + cell.getY());
					neighbors.add(cell);
				}
			}
		}
		System.out.println("Neighbors: " + neighbors);
		return neighbors;
	}

}
