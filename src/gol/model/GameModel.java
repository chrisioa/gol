package gol.model;

import java.util.ArrayList;

public class GameModel {

	private int gamewidth;
	private int gameheight;
	private double cellSize;
	//private GameLogic logic;
	
	private ArrayList<GameCell> gameCells = new ArrayList<>();

	public GameModel(int width, int height) {

		setGameheight(height);
		setGamewidth(width);

		// initialize gameCells
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				GameCell gc = new GameCell(i, j, false);
				gameCells.add(gc);
			}
		}
		
		//logic = new GameLogic(this);

	}

	public void startGame(){
		//logic.startGame();
	}
	
	// Activate GameCell
	public void activateGameCell(double layoutX, double layoutY) {
		for (GameCell cell : gameCells) {
			if (layoutX == cell.getX() && layoutY == cell.getY()) {
				cell.setAlive(true);
				//logic.checkSurroundings(cell);
				break;
			}
		}
	}

	public void deactivateGameCell(double layoutX, double layoutY) {
		for (GameCell cell : gameCells) {
			if (layoutX == cell.getX() && layoutY == cell.getY()) {
				cell.setAlive(false);
				//logic.checkSurroundings(cell);
				break;
			}
		}
	}
	
	public void deactivateGameCellNOCHECK(double layoutX, double layoutY){
		for (GameCell cell : gameCells) {
			if (layoutX == cell.getX() && layoutY == cell.getY()) {
				cell.setAlive(false);
				break;
			}
		}
	}

	public boolean isAlive(double x, double y) {
		for (GameCell cell : gameCells) {
			if (cell.getY() == y) {
				if (cell.getY() == x) {
					return cell.isAlive();
				}
			}
		}
		return false;
	}

	// Getter and Setter for variables

	public int getGamewidth() {
		return gamewidth;
	}

	public void setGamewidth(int gamewidth) {
		this.gamewidth = gamewidth;
	}

	public int getGameheight() {
		return gameheight;
	}

	public void setGameheight(int gameheight) {
		this.gameheight = gameheight;
	}

	public ArrayList<GameCell> getGameCells() {
		return gameCells;
	}

	public void setCellSize(Integer cellSize) {
		this.cellSize = cellSize;
	}
	
	public double getCellSize(){
		return cellSize;
	}

	public void deactivateAllGameCells() {
		for(GameCell gc : gameCells){
			gc.setAlive(false);
		}
	}

}
