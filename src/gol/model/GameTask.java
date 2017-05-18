package gol.model;

import java.util.ArrayList;

import gol.control.GameController;
import javafx.beans.property.BooleanProperty;

public class GameTask implements Runnable {
	private BooleanProperty[][] gameCells;
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

			ArrayList<BooleanProperty> neighbors;
			ArrayList<BooleanProperty> toActivate = new ArrayList<>();
			ArrayList<BooleanProperty> toDeactivate = new ArrayList<>();
			// Check surroundings for every cell and either add it to toActivate
			// or toDeactivate
			for (int i =0; i<gameCells.length; i++) {
				for(int j = 0; j < gameCells[i].length; j++){
					
				neighbors = new ArrayList<>();
				neighbors = checkSurroundings(i,j);
				if (gameCells[i][j].get() && neighbors.size() < 2 || gameCells[i][j].get() && neighbors.size() > 3) {
					toDeactivate.add(gameCells[i][j]);
				} else if (neighbors.size() == 3 && !gameCells[i][j].get()) {
					toActivate.add(gameCells[i][j]);
				}
				}
			}
			//Activate cells
			for (BooleanProperty thisCell : toActivate) {
				thisCell.set(true);
			}
			//Deactivate cells
			for (BooleanProperty thisCell : toDeactivate) {
				thisCell.set(false);
			}
	}

	public void pause(){
		running = false;
	}
	
	// Return a List of Neighbours for specified cell
	private ArrayList<BooleanProperty> checkSurroundings(int layoutX, int layoutY) {

		ArrayList<BooleanProperty> neighbors = new ArrayList<>();
		BooleanProperty[][] gameCells = controller.getGameCells();

		for (int i = 0; i<gameCells.length; i++) {
			for(int j = 0; j<gameCells[i].length; j++){
				
			// Left, Right Hand Neighbors
			if (layoutX - 1 == i && layoutY == j
					|| layoutX + 1 ==i && layoutY == j ||
					// Upper, Lower Neighbors
					layoutX ==i && layoutY - 1 == j
					|| layoutX == i && layoutY + 1 ==j ||
					// Diagonal Neighbors
					layoutX - 1 ==i && layoutY - 1 == j
					|| layoutX - 1 ==i && layoutY + 1 == j
					|| layoutX + 1 == i && layoutY - 1 == j
					|| layoutX + 1 == i && layoutY + 1 == j) {

				if (gameCells[i][j].get()) {
					neighbors.add(gameCells[i][j]);
				}
			}
			}
		}
		return neighbors;
	}

	public int getGameSpeed() {
		return controller.getGameSpeed();
	}

}
