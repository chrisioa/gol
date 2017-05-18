package gol.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameModel {

	private int gamewidth;
	private int gameheight;
	private double cellSize;
	// private GameLogic logic;

	// private ArrayList<GameCell> gameCells = new ArrayList<>();

	private BooleanProperty[][] gameCells;

	public GameModel(int width, int height) {

		setGameheight(height);
		setGamewidth(width);
		gameCells = new BooleanProperty[width][height];
		// initialize gameCells
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				gameCells[i][j] = new SimpleBooleanProperty(false);
			}
		}

	}

	// Activate GameCell
	public void activateGameCell(int layoutX, int layoutY) {
		gameCells[layoutX][layoutY].set(true);
	}

	public void deactivateGameCell(int layoutX, int layoutY) {
		gameCells[layoutX][layoutY].set(false);
	}

	public boolean isAlive(int x, int y) {

		return gameCells[x][y].get();
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

	public BooleanProperty[][] getGameCells() {
		return gameCells;
	}

	public void setCellSize(Integer cellSize) {
		this.cellSize = cellSize;
	}

	public double getCellSize() {
		return cellSize;
	}

	public void deactivateAllGameCells() {
		for (int i = 0; i < gameCells.length; i++) {
			for (int j = 0; j < gameCells[i].length; j++) {
				gameCells[i][j].set(false);
			}
		}
	}

}
